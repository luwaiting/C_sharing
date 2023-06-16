package com.example.csharing.controller;

import com.example.csharing.domain.CourseBoard;
import com.example.csharing.domain.IdentityConfirm;
import com.example.csharing.domain.User;
import com.example.csharing.domain.UserNickName;
import com.example.csharing.domain.posting.Posting;
import com.example.csharing.domain.posting.PostingComment;
import com.example.csharing.domain.vision.Vision;
import com.example.csharing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
public class ProfileController {
    @Value("${SavePath.ProfilePhoto}")
    private String ProfilePhotoSavePath;
    @Value("${SavePath.ProfilePhotoMapper}")
    private String ProfilePhotoMapperPath;
    @Autowired
    UserService userService;
    @Autowired
    PostingService postingService;
    @Autowired
    PostingCommentService postingCommentService;
    @Autowired
    CourseBoardService courseBoardService;
    @Autowired
    IdentityVerifyService identityVerifyService;
    @Autowired
    UserNickNameService userNickNameService;
    @GetMapping("/personalProfile/{id}")
    public String personalProfile(@PathVariable("id")Long accountId, Model model){
        User user= userService.findUserById(accountId);
        List<CourseBoard> courseBoards=courseBoardService.findByDescSize();
        if(user.getIdentity()==1){
            model.addAttribute("postNumber",user.getPosting().size());
            model.addAttribute("userInfo",user);
            model.addAttribute("postLikeNumber",postingService.findAllLikeNumber(accountId));
            model.addAttribute("commentLikeNumber",postingCommentService.findAllLike(accountId));
            model.addAttribute("nickname",user.getNickNameList());
            model.addAttribute("accountId",accountId);
            model.addAttribute("allCourseBoard",courseBoards);
            return "EN-personalProfile";
        }
        model.addAttribute("postNumber",user.getPosting().size());
        model.addAttribute("userInfo",user);
        model.addAttribute("postLikeNumber",postingService.findAllLikeNumber(accountId));
        model.addAttribute("commentLikeNumber",postingCommentService.findAllLike(accountId));
        model.addAttribute("nickname",user.getNickNameList());
        model.addAttribute("accountId",accountId);
        model.addAttribute("allCourseBoard",courseBoards);
        return "personalProfile";
    }
    @PostMapping("/updateDisplayedName")
    public String updateDisplayedName(@RequestParam("displayedName")String displayedName,
                                      @RequestParam("accountId")Long accountId,
                                      RedirectAttributes redirectAttributes){
        User user= userService.findUserById(accountId);
        user.setDisplayedName(displayedName);
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message","Your displayed name "+displayedName+" has updated");
        return "redirect:/personalProfile/"+accountId;
    }
    //updateSelf
    @PostMapping("/updateSelf")
    public String updateSelf(@RequestParam("self")String self,
                                      @RequestParam("accountId")Long accountId){
        User user= userService.findUserById(accountId);
        user.setIntroduction(self);
        userService.saveUser(user);
        return "redirect:/personalProfile/"+accountId;
    }
    @PostMapping("/verifyId")
    public String verifyId(IdentityConfirm identityConfirm,
                           @RequestParam("accountId")Long accountId,
                           @RequestParam("file")MultipartFile multipartFile,
                           RedirectAttributes redirectAttributes){
        User user= userService.findUserById(accountId);
       identityVerifyService.save(identityConfirm);
        String fileName=multipartFile.getOriginalFilename();
        String suffixName=fileName.substring(fileName.lastIndexOf("."));
        fileName= UUID.randomUUID()+suffixName;
        try {
            multipartFile.transferTo(new File(ProfilePhotoSavePath+fileName));
            String path=ProfilePhotoSavePath+fileName;
//            redirectAttributes.addFlashAttribute("path",path);
//            return ProfilePhotoMapperPath+fileName;
            System.out.println("執行成功 "+path);
            Vision vision=new Vision();
            List<String>verifyResult=vision.confirm(vision.analyzeImage(path),identityConfirm.getSchool(),identityConfirm.getDepartment());
            if(!verifyResult.isEmpty()){
                String m="";
                int c=0;
                for(String n:verifyResult){
                    UserNickName userNickName=new UserNickName();
                    userNickName.setNickname(n);
                    userNickName.setUser(user);
                    userNickNameService.saveNickName(userNickName);
                    m=c==0?m+n:m+","+n;
                    c++;
                }
                redirectAttributes.addFlashAttribute("message","You have got "+m+" nickname");
            }else{
                redirectAttributes.addFlashAttribute("message2","something goes wrong .Please try again or contact us.");
            }

        }catch (Exception e){
            System.out.println("執行失敗");
            e.printStackTrace();
        }
        return "redirect:/personalProfile/"+accountId;
    }
    @GetMapping("/myPost/{id}")
    public String myPost(@PathVariable("id")Long accountId,Model model){
        User user= userService.findUserById(accountId);
        List<Posting>postingList=postingService.findAllByUserId(accountId);
        List<CourseBoard> courseBoards=courseBoardService.findByDescSize();
        if(user.getIdentity()==1){
            model.addAttribute("myPost",postingList);
            model.addAttribute("allCourseBoard",courseBoards);
            model.addAttribute("accountId",accountId);
            model.addAttribute("savePost",user.getSavePosting());
            return "EN-myPostAndSaving";
        }
        model.addAttribute("myPost",postingList);
        model.addAttribute("accountId",accountId);
        model.addAttribute("allCourseBoard",courseBoards);
        model.addAttribute("savePost",user.getSavePosting());
        return "myPostAndSaving";
    }
    @PostMapping("/deleteSavePost")
    public String deleteSavePost(@RequestParam("accountId")Long accountId,
                                 @RequestParam("postingId")Long postingId,
                                 RedirectAttributes redirectAttributes){
        userService.unSavePosting(accountId,postingId);
        redirectAttributes.addFlashAttribute("message","delete success");
        return "redirect:/myPost/"+accountId;
    }
    @GetMapping("/followList/{id}")
    public String followList(@PathVariable("id")Long accountId,Model model){
        User user= userService.findUserById(accountId);
        List<CourseBoard> courseBoards=courseBoardService.findByDescSize();
        if(user.getIdentity()==1){

            model.addAttribute("allCourseBoard",courseBoards);
            model.addAttribute("accountId",accountId);
            model.addAttribute("followList",user.getCourseBoards());
            return "EN-followList";
        }
        model.addAttribute("accountId",accountId);
        model.addAttribute("allCourseBoard",courseBoards);
        model.addAttribute("followList",user.getCourseBoards());
        return "followList";
    }
    @GetMapping("/userProfile/{userProfileId}/{accountId}")
    public String userProfile(@PathVariable("accountId")Long accountId,
            @PathVariable("userProfileId")Long userProfileId
            ,Model model){
        User user= userService.findUserById(accountId);
        List<CourseBoard> courseBoards=courseBoardService.findByDescSize();
        User target=userService.findUserById(userProfileId);

        if(user.getIdentity()==1){
            model.addAttribute("accountId",accountId);
            model.addAttribute("allCourseBoard",courseBoards);
            model.addAttribute("userInfo",target);
            model.addAttribute("allPost",target.getPosting());
            model.addAttribute("postNumber",target.getPosting().size());
            return "EN-otherUserProfile";
        }
        model.addAttribute("accountId",accountId);
        model.addAttribute("allCourseBoard",courseBoards);
        model.addAttribute("userInfo",target);
        model.addAttribute("allPost",target.getPosting());
        model.addAttribute("postNumber",target.getPosting().size());
        return "otherUserProfile";
    }

    @PostMapping("/pressLike")
    public String press(@RequestParam("accountId")Long accountId,
                        @RequestParam("userProfileId")Long userProfileId){
        User user= userService.findUserById(userProfileId);
        user.setLoveNumber(user.getLoveNumber()+1);
        userService.saveUser(user);
        return "redirect:/userProfile/"+userProfileId+"/"+accountId;
    }
}
