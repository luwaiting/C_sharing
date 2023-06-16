package com.example.csharing.controller;

//import com.example.csharing.dao.StudentRepository;
//import com.example.csharing.domain.Student;
import com.example.csharing.domain.CourseBoard;
import com.example.csharing.domain.User;
import com.example.csharing.domain.UserNickName;
import com.example.csharing.domain.posting.Posting;
import com.example.csharing.form.ConfirmForm;
import com.example.csharing.form.LoginForm;
import com.example.csharing.form.RegisterForm;
//import com.example.csharing.service.StudentService;
import com.example.csharing.service.CourseBoardService;
import com.example.csharing.service.PostingService;
import com.example.csharing.service.UserNickNameService;
import com.example.csharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegisterController {
//    @Autowired
//    private StudentService studentService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserNickNameService userNickNameService;
    @Autowired
    private PostingService postingService;
    @Autowired
    private CourseBoardService courseBoardService;
    @GetMapping("/registerPage")
    public String registerPage(Model model){
        model.addAttribute("registerForm",new RegisterForm());
        return "register";
    }
    @GetMapping("/confirmRegisterPage")
    public String confirmRegisterPage(Model model){
        model.addAttribute("confirmForm",new ConfirmForm());
        return "confirmRegister";
    }
//    @PostMapping("/confirmRegisterPage")
//    public String confirm(@RequestParam("userConfirm")String inputConfirm,
//                          @RequestParam("accountId")Long accountId){
//
//        return "confirmRegister";
//    }
    @PostMapping("/confirmRegisterPage")
    public String confirm(@Valid ConfirmForm confirmForm,BindingResult bindingResult,
                          @RequestParam("token")String token,
                          @RequestParam("accountId")Long accountId,
                          RedirectAttributes redirectAttributes,
                          Model model){
        if(!confirmForm.confirm(token)){
            bindingResult.rejectValue("confirmCode", "confirmError", "your confirm code is incorrect");
            model.addAttribute("accountId",accountId);
            model.addAttribute("token",token);

        }else {
            User user=userService.findUserById(accountId);
            if(user.getIdentity()==1){
                if (user.getNickNameList().isEmpty()) {
                    UserNickName userNickName = new UserNickName();
                    userNickName.setNickname("Anonymous");
                    userNickName.setUser(user);
                    user.setDisplayedName("Anonymous");
                    userNickNameService.saveNickName(userNickName);
                }
            }else{
                if (user.getNickNameList().isEmpty()) {
                    UserNickName userNickName = new UserNickName();
                    userNickName.setNickname("匿名");
                    userNickName.setUser(user);
                    user.setDisplayedName("匿名");
                    userNickNameService.saveNickName(userNickName);
                }
            }
            user.setActivated(1);
            user.settScore(100);
            userService.saveUser(user);
            // undo移到confirmRegisterPage
            redirectAttributes.addFlashAttribute("message",user.getEmail()+" register successfully！");
            return "redirect:/loginPage";
        }
        return "confirmRegister";
    }

    // account model
    @GetMapping("/homePage/{id}")
    public String homePage(@PathVariable("id")Long accountId, Model model){
        User user=userService.findUserById(accountId);
        List<Posting>allPostingList=null;
        List<Posting> requiredPosting=null;
        List<Posting> generalPosting=null;
        List<CourseBoard>courseBoards=courseBoardService.findByDescSize();
        if(user.getIdentity()==1){
            allPostingList=postingService.findPostingByIdentity(1);
            requiredPosting=postingService.findPostingByIdentityAndType(1,0);
            generalPosting=postingService.findPostingByIdentityAndType(1,1);
            model.addAttribute("accountId", accountId);
            model.addAttribute("allPost",allPostingList);
            model.addAttribute("requiredPost",requiredPosting);
            model.addAttribute("generalPost",generalPosting);
            model.addAttribute("allCourseBoard",courseBoards);
            return "EN-nccu";
        }
        allPostingList=postingService.findPostingByIdentity(0);
        requiredPosting=postingService.findPostingByIdentityAndType(0,0);
        generalPosting=postingService.findPostingByIdentityAndType(0,1);
        model.addAttribute("accountId", accountId);
        model.addAttribute("allPost",allPostingList);
        model.addAttribute("requiredPost",requiredPosting);
        model.addAttribute("generalPost",generalPosting);
        model.addAttribute("allCourseBoard",courseBoards);
        return "nccu";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model){
        model.addAttribute("loginForm",new LoginForm());
        return "login";
    }
    // 0=Local/必選修
    @PostMapping("/loginPage")
    public String login(@Valid LoginForm loginForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        String email = loginForm.getEmail();
        User user = userService.findByEmail(email);
        //介面是否符合設定
        if (bindingResult.hasErrors()){
            return "login";
        }
        //邏輯判斷是否正確存在
        if(user!=null) {
            if (user.activeOrNot()) {
                String password=DigestUtils.md5DigestAsHex(loginForm.getPassword().getBytes());
                boolean confirm = user.getPassword().equals(password);
//        System.out.println(user.getPassword()+"/"+loginForm.getPassword());
                if (confirm) {
                    //傳遞account,new a nick

                   return "redirect:/homePage/"+user.getId();
                } else {
                    bindingResult.rejectValue("password", "confirmError", "your password is incorrect");
                }
            }else{
                // undo 讓使用者重新驗證
                bindingResult.rejectValue("email", "confirmError", "This email doesn't confirm completely");
            }
        }else{
            bindingResult.rejectValue("email", "confirmError", "This email doesn't register");
        }
//        "redirect:/confirmRegisterPage";
        return "login";
    }

    @PostMapping("/registerPage")
    public String register(@Valid RegisterForm registerForm, BindingResult bindingResult,RedirectAttributes redirectAttributes){
        if(!registerForm.confirmPassword()){
            bindingResult.rejectValue("ensurePassword","confirmError","difference between password!");
        }
        if (bindingResult.hasErrors()){
            return "register";
        }
        User user=registerForm.convert();
        User u=userService.findByEmail(user.getEmail());
        if(u!=null){
            bindingResult.rejectValue("email","confirmError","This email has been registered");
            return "register";
        }
        String hashedPassword = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(hashedPassword);
        User users=userService.saveUser(user);
        String token=userService.sendEmail(users.getId());
        if(!token.equals("null")){
            redirectAttributes.addFlashAttribute("token",token);
        }
        // like studentPost
        redirectAttributes.addFlashAttribute("accountId",users.getId());
        redirectAttributes.addFlashAttribute("email","Please check this email for confirm code ' "+users.getEmail()+" '");
        return "redirect:/confirmRegisterPage";
//        return "redirect:/loginPage";

    }


}
