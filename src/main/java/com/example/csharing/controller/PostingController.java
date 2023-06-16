package com.example.csharing.controller;

import com.example.csharing.domain.CourseBoard;
import com.example.csharing.domain.Search;
import com.example.csharing.domain.Sort;
import com.example.csharing.domain.User;
import com.example.csharing.domain.posting.Posting;
import com.example.csharing.domain.posting.PostingComment;
import com.example.csharing.domain.report.ReportPosting;
import com.example.csharing.service.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PostingController {
    @Autowired
    PostingService postingService;
    @Autowired
    UserService userService;
    @Autowired
    CourseBoardService courseBoardService;
    @Autowired
    SearchService searchService;
    @Autowired
    PostingCommentService postingCommentService;
    @Autowired
    ReportPostingService reportPostingService;
    @Autowired
    ReportCommentService reportCommentService;
    @PostMapping("/postingLike")
    public String likePost(@RequestParam("accountId")Long accountId,
                           @RequestParam("i")String i,
                           @RequestParam("postingId")Long postingId){
        System.out.println(i);
        Posting posting=postingService.findPosting(postingId);
        posting.setLoveNumber(posting.getLoveNumber()+1);
        posting=postingService.addPosting(posting);
        return "redirect:/seePosting/"+postingId+"/"+accountId;
    }
    @PostMapping("/storePosting")
    public String storePost(@RequestParam("accountId")Long accountId,
                           @RequestParam("i")String i,
                           @RequestParam("postingId")Long postingId,
                            RedirectAttributes redirectAttributes){
        userService.savePosting(accountId,postingId);
        User user=userService.findUserById(accountId);
        for(Posting p:user.getSavePosting()){
            System.out.println(p.getCourseName());
        }
        redirectAttributes.addFlashAttribute("message","Store success!");
        return "redirect:/seePosting/"+postingId+"/"+accountId;
    }



    @GetMapping("/enResult")
    public String enSearchResult(){
        return "EN-searchResult";
    }
    @GetMapping("/chResult")
    public String chSearchResult(){
        return "searchResult";
    }

    @PostMapping("/search")
    public String search(@RequestParam("search")String key,
                         @RequestParam("accountId")Long accountId,
                         RedirectAttributes redirectAttributes){
        User user =userService.findUserById(accountId);
        Search search=new Search();
        search.setKeyword(key);
        search=searchService.save(search);
        userService.saveSearch(accountId,search);

        List<CourseBoard> courseBoard=courseBoardService.findCourseBoardByKeyword(key);;
        if(user.getIdentity()==1){
            if(courseBoard.isEmpty()){
                redirectAttributes.addFlashAttribute("message","Sorry,we didn't find any post about「 "+key+" 」");
            }else {
                redirectAttributes.addFlashAttribute("message","We got "+courseBoard.size()+" course board about 「 "+key+" 」");
            }
            redirectAttributes.addFlashAttribute("allCourseBoard",courseBoard);
            redirectAttributes.addFlashAttribute("accountId",accountId);
            redirectAttributes.addFlashAttribute("input",key);
            return "redirect:/enResult";
        }
        if(courseBoard.isEmpty()){
            redirectAttributes.addFlashAttribute("message","抱歉,我們沒有找到任何關於「 "+key+" 」的課程心得");
        }else {
            redirectAttributes.addFlashAttribute("message","我們找到 "+courseBoard.size()+" 個關於 「 "+key+" 」的課程版");
        }
        redirectAttributes.addFlashAttribute("allCourseBoard",courseBoard);
        redirectAttributes.addFlashAttribute("accountId",accountId);
        redirectAttributes.addFlashAttribute("input",key);
        return "redirect:/chResult";
    }

    @GetMapping("/postPage/{id}")
    public String postPage(@PathVariable long id, Model model){
        System.out.println(id);
        User user=userService.findUserById(id);
        System.out.println(user.getName());
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm");
        String date=ft.format(dNow);
        if(user.getIdentity()==1){
            model.addAttribute("time",date);
            model.addAttribute("accountId",user.getId());
            model.addAttribute("nickname",user.getNickNameList());
            model.addAttribute("posting",new Posting());
            return "EN-studentPost";
        }
        model.addAttribute("time",date);
        model.addAttribute("accountId",user.getId());
        model.addAttribute("nickname",user.getNickNameList());
        model.addAttribute("posting",new Posting());

        return "studentPost";
    }
    @GetMapping("/updatePost/{id}/{accountId}")
    public String updatePost(@PathVariable long id,
            @PathVariable("accountId")Long accountId
            , Model model){
//        System.out.println(accountId);
        User user=userService.findUserById(accountId);
        Posting posting=postingService.findPosting(id);
        System.out.println(user.getName());
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm");
        String date=ft.format(dNow);
        if(user.getIdentity()==1){
            model.addAttribute("time",date);
            model.addAttribute("accountId",user.getId());
            model.addAttribute("nickname",user.getNickNameList());
            model.addAttribute("posting",posting);
            return "EN-studentPost";
        }
        model.addAttribute("time",date);
        model.addAttribute("accountId",user.getId());
        model.addAttribute("nickname",user.getNickNameList());
        model.addAttribute("posting",posting);

        return "studentPost";
    }

    @PostMapping("/postPage")
    public String post(@Valid Posting posting, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model,
                       @RequestParam("accountId")Long accountId) {
        User user=userService.findUserById(accountId);
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd HH:mm");
        String date=ft.format(dNow);
        Date time=null;
        System.out.println(date);
        try {
            time=ft.parse(date);
        } catch (ParseException e) {
            System.out.println("posting time occur error!");
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("posting",posting);
            model.addAttribute("accountId",user.getId());
            model.addAttribute("nickname",user.getNickNameList());
            //"redirect:/postPage/"+user.getId()
            return "studentPost";
        }
        posting.setUser(user);
        posting.setPostDate(time);
        posting=postingService.addPosting(posting);
        // done --> courseBoard(courseBoardService-->include post
        String postingName=posting.getCourseName();
        List<CourseBoard> courseBoard=courseBoardService.findCourseBoardByKeyword(postingName);
        if(courseBoard.isEmpty()){
            CourseBoard board=new CourseBoard();
            board.setBoardName(postingName);
            board.setCourseType(posting.getCourseType());
            courseBoardService.includePosting(posting,board);
            board=courseBoardService.addCourseBoard(board);

//            test for whether it success to relation
            for(CourseBoard c:posting.getCourseBoard()){
                System.out.println(c.getBoardName());
            }
        }else{
            for (CourseBoard c:courseBoard){
                courseBoardService.includePosting(posting,c);
            }
        }
//        List<CourseBoard>courseBoardList=courseBoardService.findAll();
//        List<CourseBoard>courseBoards=new Sort().sortBoardByScore(courseBoardList,0,courseBoardList.size()-1);
        if(user.getIdentity()==1){
            redirectAttributes.addFlashAttribute("message",postingName+" post ");
            return "redirect:/homePage/"+accountId;
        }
         redirectAttributes.addFlashAttribute("message",postingName+" post ");
        return "redirect:/homePage/"+accountId;
    }
    @GetMapping("/postingOrder/{id}/{order}")
    public String postingOrder(@PathVariable("id")Long accountId,
                               @PathVariable("order")int order,
                               Model model){
        User user=userService.findUserById(accountId);
        List<Posting>allPostingList=null;
        List<Posting> requiredPosting=null;
        List<Posting> generalPosting=null;
        List<CourseBoard>courseBoards=courseBoardService.findByDescSize();
//        List<CourseBoard>courseBoardList=courseBoardService.findAll();
//        List<CourseBoard>courseBoards=new Sort().sortBoardByScore(courseBoardList,0,courseBoardList.size()-1);
        if(user.getIdentity()==1){
            if(order==1){
                allPostingList=postingService.findAllByDescLike(1);
                requiredPosting=postingService.findByCourseTypeOrderByLike(1,0);
                generalPosting=postingService.findByCourseTypeOrderByLike(1,1);
                model.addAttribute("accountId", user.getId());
                model.addAttribute("allPost",allPostingList);
                model.addAttribute("requiredPost",requiredPosting);
                model.addAttribute("generalPost",generalPosting);
                model.addAttribute("allCourseBoard",courseBoards);
                model.addAttribute("message",null);
                model.addAttribute("postingOrder",order);
            }else{
                allPostingList=postingService.findAllByDescTime(1);
                requiredPosting=postingService.findByCourseTypeOrderByTime(1,0);
                generalPosting=postingService.findByCourseTypeOrderByTime(1,1);
                model.addAttribute("accountId", user.getId());
                model.addAttribute("allPost",allPostingList);
                model.addAttribute("requiredPost",requiredPosting);
                model.addAttribute("generalPost",generalPosting);
                model.addAttribute("allCourseBoard",courseBoards);
                model.addAttribute("message",null);
                model.addAttribute("postingOrder",order);
            }
            return "EN-nccu";
        }
        if(order==1){
            allPostingList=postingService.findAllByDescLike(0);
            requiredPosting=postingService.findByCourseTypeOrderByLike(0,0);
            generalPosting=postingService.findByCourseTypeOrderByLike(0,1);
            model.addAttribute("accountId", user.getId());
            model.addAttribute("allPost",allPostingList);
            model.addAttribute("requiredPost",requiredPosting);
            model.addAttribute("generalPost",generalPosting);
            model.addAttribute("allCourseBoard",courseBoards);
            model.addAttribute("message",null);
            model.addAttribute("postingOrder",order);
        }else{
            allPostingList=postingService.findAllByDescTime(0);
            requiredPosting=postingService.findByCourseTypeOrderByTime(0,0);
            generalPosting=postingService.findByCourseTypeOrderByTime(0,1);
            model.addAttribute("accountId", user.getId());
            model.addAttribute("allPost",allPostingList);
            model.addAttribute("requiredPost",requiredPosting);
            model.addAttribute("generalPost",generalPosting);
            model.addAttribute("allCourseBoard",courseBoards);
            model.addAttribute("message",null);
            model.addAttribute("postingOrder",order);
        }
        return "nccu";
    }
    @GetMapping("/seePosting/{id}/{accountId}")
    public String seePosting(@PathVariable("id")Long id,
                             @PathVariable("accountId")Long accountId,
                             Model model){
        Posting posting=postingService.findPosting(id);
        User user=userService.findUserById(accountId);
        List<CourseBoard>courseBoards=courseBoardService.findAll();
        List<PostingComment>comments=posting.getPostingComment();
        if(user.getIdentity()==1){
            model.addAttribute("posting",posting);
            model.addAttribute("accountId",accountId);
            model.addAttribute("allCourseBoard",courseBoards);
            model.addAttribute("comments",comments);
            model.addAttribute("nickname",user.getNickNameList());

            return "EN-nccuPosting";
        }
        model.addAttribute("posting",posting);
//        model.addAttribute("whoPost",posting.getUser().);
        model.addAttribute("accountId",accountId);
        model.addAttribute("allCourseBoard",courseBoards);
        model.addAttribute("comments",comments);
        model.addAttribute("nickname",user.getNickNameList());

        return "nccuPosting";
    }
    @GetMapping("/nccuPosting/{id}/{accountId}")
    public String nccuPost(@PathVariable("id") Long id,
                           @PathVariable("accountId") Long accountId,
                           Model model){
        Posting posting=postingService.findPosting(id);
        User user=userService.findUserById(accountId);
        List<CourseBoard>courseBoards=courseBoardService.findAll();
        if(user.getIdentity()==1){
            model.addAttribute("posting",posting);
            model.addAttribute("accountId",accountId);
            model.addAttribute("allCourseBoard",courseBoards);
            model.addAttribute("nickname",user.getNickNameList());
            return "EN-nccuPosting";
        }
        model.addAttribute("posting",posting);
//        model.addAttribute("whoPost",posting.getUser().);
        model.addAttribute("accountId",accountId);
        model.addAttribute("allCourseBoard",courseBoards);
        model.addAttribute("nickname",user.getNickNameList());
        return "nccuPosting";

    }
    @PostMapping("/OrderByPop")
    public String orderCommentByP(@RequestParam("accountId")Long accountId,
                                  @RequestParam("postingId")Long postingId,
                                  RedirectAttributes redirectAttributes){
        List<PostingComment>comments=postingCommentService.findCommentOrderByPopular(postingId);
        redirectAttributes.addFlashAttribute("comments",comments);
        return "redirect:/nccuPosting/"+postingId+"/"+accountId;

    }
    @PostMapping("/OrderByTime")
    public String orderCommentByT(@RequestParam("accountId")Long accountId,
                                  @RequestParam("postingId")Long postingId,
                                  RedirectAttributes redirectAttributes){
        List<PostingComment>comments=postingCommentService.findCommentOrderByTime(postingId);
        redirectAttributes.addFlashAttribute("comments",comments);
        return "redirect:/nccuPosting/"+postingId+"/"+accountId;

    }

    @GetMapping("/En/{accountId}")
    public String toEn(@PathVariable("accountId")Long accountId,
                               Model model){
        User user=userService.findUserById(accountId);
        List<Posting>allPostingList=null;
        List<Posting> requiredPosting=null;
        List<Posting> generalPosting=null;
        List<CourseBoard>courseBoards=courseBoardService.findByDescSize();
//        List<CourseBoard>courseBoardList=courseBoardService.findAll();
//        List<CourseBoard>courseBoards=new Sort().sortBoardByScore(courseBoardList,0,courseBoardList.size()-1);
        if(user.getIdentity()==1){
            allPostingList=postingService.findPostingByIdentity(1);
            requiredPosting=postingService.findPostingByIdentityAndType(1,0);
            generalPosting=postingService.findPostingByIdentityAndType(1,1);
            model.addAttribute("accountId", user.getId());
            model.addAttribute("allPost",allPostingList);
            model.addAttribute("requiredPost",requiredPosting);
            model.addAttribute("generalPost",generalPosting);
            model.addAttribute("allCourseBoard",courseBoards);
            return "EN-nccu";
        }
        allPostingList=postingService.findPostingByIdentity(1);
        requiredPosting=postingService.findPostingByIdentityAndType(1,0);
        generalPosting=postingService.findPostingByIdentityAndType(1,1);
        model.addAttribute("accountId", user.getId());
        model.addAttribute("allPost",allPostingList);
        model.addAttribute("requiredPost",requiredPosting);
        model.addAttribute("generalPost",generalPosting);
        model.addAttribute("allCourseBoard",courseBoards);
        return "nccu";
    }
    @GetMapping("/Ch/{accountId}")
    public String toCh(@PathVariable("accountId")Long accountId,
                       Model model){
        User user=userService.findUserById(accountId);
        List<Posting>allPostingList=null;
        List<Posting> requiredPosting=null;
        List<Posting> generalPosting=null;
        List<CourseBoard>courseBoards=courseBoardService.findByDescSize();
//        List<CourseBoard>courseBoardList=courseBoardService.findAll();
//        List<CourseBoard>courseBoards=new Sort().sortBoardByScore(courseBoardList,0,courseBoardList.size()-1);
        if(user.getIdentity()==1){
            allPostingList=postingService.findPostingByIdentity(0);
            requiredPosting=postingService.findPostingByIdentityAndType(0,0);
            generalPosting=postingService.findPostingByIdentityAndType(0,1);
            model.addAttribute("accountId", user.getId());
            model.addAttribute("allPost",allPostingList);
            model.addAttribute("requiredPost",requiredPosting);
            model.addAttribute("generalPost",generalPosting);
            model.addAttribute("allCourseBoard",courseBoards);
            return "EN-nccu";
        }
        allPostingList=postingService.findPostingByIdentity(0);
        requiredPosting=postingService.findPostingByIdentityAndType(0,0);
        generalPosting=postingService.findPostingByIdentityAndType(0,1);
        model.addAttribute("accountId", user.getId());
        model.addAttribute("allPost",allPostingList);
        model.addAttribute("requiredPost",requiredPosting);
        model.addAttribute("generalPost",generalPosting);
        model.addAttribute("allCourseBoard",courseBoards);
        return "nccu";
    }
    @GetMapping("/board/{id}/{accountId}")
    public String board(@PathVariable Long id,@PathVariable Long accountId,Model model){
        CourseBoard courseBoard=courseBoardService.findCourseBoardById(id);
        User user=userService.findUserById(accountId);
        List<Posting>postingList=courseBoard.getPostingList();
        List<CourseBoard>courseBoards=courseBoardService.findAll();
        if(user.getIdentity()==1){
            model.addAttribute("courseBoard",courseBoard);
            model.addAttribute("postingList",postingList);
            model.addAttribute("accountId",accountId);
            model.addAttribute("allCourseBoard",courseBoards);
            return "EN-nccuCourse";
        }
        model.addAttribute("courseBoard",courseBoard);
        model.addAttribute("postingList",postingList);
        model.addAttribute("accountId",accountId);
        model.addAttribute("allCourseBoard",courseBoards);
        return "nccuCourse";
    }
    @PostMapping("/follow")
    public String follow(@RequestParam("accountId")Long accountId,
                         @RequestParam("courseBoardId")Long courseBoardId,
                         @RequestParam("i")String i,
                         RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message","Follow success!");
        userService.followBoard(accountId,courseBoardId);
        return "redirect:/board/"+courseBoardId+"/"+accountId;

    }
    @PostMapping("/reportPost")
    public String reportPost(@RequestParam("accountId")Long accountId,
                             @RequestParam("postingId")Long postingId,
                             @RequestParam("content")String content,
                             RedirectAttributes redirectAttributes){
        reportPostingService.saveReport(accountId,postingId, content);
        Posting posting=postingService.findPosting(postingId);
        redirectAttributes.addFlashAttribute("message","We have receive your report for "+posting.getCourseName());
        return "redirect:/homePage/"+accountId;
    }
    @PostMapping("/reportComment")
    public String reportComment(@RequestParam("accountId")Long accountId,
                             @RequestParam("commentId")Long commentId,
                             @RequestParam("content")String content,
                             RedirectAttributes redirectAttributes){
        reportCommentService.saveReportComment(accountId, commentId, content);
        PostingComment postingComment=postingCommentService.findComment(commentId);
        redirectAttributes.addFlashAttribute("message","We have receive your report for this user Id "+postingComment.getUser().getId());
        return "redirect:/homePage/"+accountId;
    }



}
