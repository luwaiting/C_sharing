package com.example.csharing.controller;

import com.example.csharing.domain.User;
import com.example.csharing.domain.posting.Posting;
import com.example.csharing.domain.posting.PostingComment;
import com.example.csharing.service.PostingCommentService;
import com.example.csharing.service.PostingService;
import com.example.csharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    PostingCommentService postingCommentService;
    @Autowired
    UserService userService;
    @Autowired
    PostingService postingService;
    @PostMapping("/commentLike")
    public String like(@RequestParam("accountId")Long accountId,
                       @RequestParam("i")String i,
                       @RequestParam("commentId")Long commentId,
                       RedirectAttributes redirectAttributes){
        PostingComment postingComment=postingCommentService.findComment(commentId);
        postingComment.setLoveNumber(postingComment.getLoveNumber()+1);
        postingComment=postingCommentService.addComment(postingComment);
        return "redirect:/seePosting/"+postingComment.getPosting().getId()+"/"+accountId;
    }
    @PostMapping("/comment")
    public String postComment(@RequestParam("content")String content,
                              @RequestParam("accountId")Long accountId,
                              @RequestParam("postingId")Long postingId,
                             @RequestParam("nickname")String nickname,
                             RedirectAttributes redirectAttributes){
        User user=userService.findUserById(accountId);
        Posting posting=postingService.findPosting(postingId);
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
        PostingComment postingComment=new PostingComment();
        postingComment.setContent(content);
        postingComment.setPosting(posting);
        postingComment.setUser(user);
        postingComment.setNickname(nickname);
        postingComment.setPostDate(time);
        postingCommentService.addComment(postingComment);
        return "redirect:/seePosting/"+postingId+"/"+accountId;
    }


}
