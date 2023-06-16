package com.example.csharing.controller;

import com.example.csharing.dao.P_commentRepository;
import com.example.csharing.dao.PostingRepository;
//import com.example.csharing.dao.StudentRepository;
import com.example.csharing.dao.UserRepository;
import com.example.csharing.domain.CourseBoard;
//import com.example.csharing.domain.Student;
import com.example.csharing.domain.Search;
import com.example.csharing.domain.Te;
import com.example.csharing.domain.User;
import com.example.csharing.domain.posting.Posting;
import com.example.csharing.domain.posting.PostingComment;
import com.example.csharing.service.CourseBoardService;
import com.example.csharing.service.SearchService;
import com.example.csharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@RestController
@Controller
public class test {
    @Autowired
    UserRepository userRepository;
//    @Autowired
//    StudentRepository studentRepository;

    @Autowired
    PostingRepository postingRepository;
    @Autowired
    P_commentRepository p_commentRepository;
    @Autowired
    UserService userService;
    @Autowired
    SearchService searchService;

    //-------------
    @Autowired
    CourseBoardService courseBoardService;

//    @GetMapping("/test")
//    public String test(){
//        User user=new User();
//        user.setName("tim");
//        user.setEmail("jkjh@gmail.com");
//        user.setIntroduction("kjhhhhhkh");
//        user.setNickname("kjhk");
//        user.setPassword("hgbkj87");
//        user.setSchool("jknk");
//        user.setSex(0);
//        Student student=new Student();
//        student.setIdentity(0);
//        student.setUser(user);
//        studentRepository.save(student);
//        return "success";
////        user.setStudent(student);
//    }
//    try add a posting and the relationship b/t user and posting
    // 在多的那一方不需手動對1的那方的值操做（關鍵在於是使用哪個JPA repository)
    @GetMapping("/test2")
    public Object test2(){
        User user=userRepository.findById(1L).orElse(null);
//        List<Posting> postingList=new ArrayList<Posting>();
        Posting posting1=new Posting();
        posting1.setCool(2);
        posting1.setCourseName("ujhhu900");
        posting1.setCourseType(1);
        posting1.setDescription("sdasafqfeqioi909");
        posting1.setSweet(4);
        posting1.setTeacherName("sadklo9");
        posting1.setUser(user);
//        user.addPosting(posting1);
        postingRepository.save(posting1);
//        for (Posting p:user.getPosting()){
//            System.out.println(p.getSweet());
//        }
//        postingList.add(posting1);
//        user.setPosting(postingList);
//        userRepository.save(user);
        return "success";
//        user.setStudent(student);
    }
    // try delete in Many
    @GetMapping("/test4")
    public Object test4(){
        Posting posting=postingRepository.findById(7L).orElse(null);
        posting.clearPosting();
        postingRepository.deleteById(posting.getId());

        return "success";
//        user.setStudent(student);
    }
    // test for add comment
    @GetMapping("/test3")
    public Object test3(){
        User user=userRepository.findById(3L).orElse(null);
        PostingComment posting_comment=new PostingComment();
        posting_comment.setContent("gooooood");
        posting_comment.setUser(user);
//        user.addComment(posting_comment);
//        userRepository.save(user);
        //another way(for many side)
        p_commentRepository.save(posting_comment);
        //test for effect
        for (PostingComment p:user.getComments()){
            System.out.println(p.getId());
        }
        return "success";
//        user.setStudent(student);
    }
    @GetMapping("/test5")
    public Object test5(){
        User user=userRepository.findById(1L).orElse(null);
        Posting posting=postingRepository.findById(3L).orElse(null);
        PostingComment posting_comment=new PostingComment();
        posting_comment.setContent("gooooood");
        posting_comment.setUser(user);
        posting_comment.setPosting(posting);
//        user.addComment(posting_comment);
//        userRepository.save(user);
        //another way(for many side)
        p_commentRepository.save(posting_comment);
        //test for effect
        for (PostingComment p:posting.getPostingComment()){
            System.out.println(p.getContent());
        }
        return "success";
//        user.setStudent(student);
    }
    // new a courseBoard
    @GetMapping("/test6")
    public Object test6(){
        CourseBoard courseBoard=new CourseBoard();
        courseBoard.setBoardName("xd1");
        courseBoardService.addCourseBoard(courseBoard);

        return "success";
//        user.setStudent(student);
    }
    //connect board with post-->無論是board中還是posting的list中，只要維護端include了就會自動存
    @GetMapping("/test7")
    public Object test7(){
//        CourseBoard courseBoard=courseBoardService.findCourseBoard(8L);
        courseBoardService.includePostingById(8L,3L);
        Posting posting=postingRepository.findById(3L).orElse(null);

        for(CourseBoard c:posting.getCourseBoard()){
            System.out.println(c.getBoardName());
        }

        return "success";
//        user.setStudent(student);
    }
    //＊＊將移除posting的動作in Service 的事務下就只需要把維護端的依賴關西去掉就好，被維護端會自動清除
    @GetMapping("/test8")
    public Object test8(){
//        CourseBoard courseBoard=courseBoardService.findCourseBoard(8L);
        courseBoardService.unIncludePosting(8L,3L);
        Posting posting=postingRepository.findById(3L).orElse(null);
        for(CourseBoard c:posting.getCourseBoard()){
            System.out.println(c.getBoardName());
        }

        return "success";
//        user.setStudent(student);
    }
    //delete for board(刪除版後，會移除板自己的資料外，共同表也會自動刪除，
    // 那版內原本的文章會自動刪除依賴關係嗎？會！！！！-->可能跟刪除是在事物下進行有關
    // （反正有牽涉到維護端的刪除用service放在事物下準沒錯
    @GetMapping("/test9")
    public Object test9(){
        courseBoardService.deleteCourseBoard(8L);
        Posting posting=postingRepository.findById(6L).orElse(null);
        for(CourseBoard c:posting.getCourseBoard()){
            System.out.println(c.getBoardName());
        }

        return "success";
    }
    @GetMapping("/test10")
    public Object test10(Model model){
        List<String> list=new ArrayList<String>();
        list.add("xd");
        list.add("xd1");
        list.add("xd2");
        model.addAttribute("list",list);
        Long i=10L;
        model.addAttribute("x",i);

        return "test";
    }
    @PostMapping("/test")
    public String test(@RequestParam Long x){

        System.out.println(x);
        return "test";
    }
    @GetMapping("/test00")
    public String tests(){
//        List<Posting>postingList=postingRepository.findByPostDate();
//        for(Posting p:postingList){
//            System.out.println(p.getSweet());
//        }
        return "test";
    }
    @GetMapping("/test01")
    public String tests1(){
        List<Posting>postingList=postingRepository.findByIdentity(0);
        for(Posting p:postingList){
            System.out.println(p.getSweet());
        }
        return "test";
    }
    @GetMapping("/test02")
    public String tests2(){
//        List<Posting>postingList=postingRepository.findByCourseType(0,0);
//        for(Posting p:postingList){
//            System.out.println(p.getSweet());
//        }
        return "test";
    }
    @GetMapping("/test03")
    public String tests3(){
        List<CourseBoard>courseBoards=courseBoardService.findCourseBoardByKeyword("123");
        if(courseBoards.isEmpty()){
            System.out.println("n~~");
        }
        return "test";
    }
    @GetMapping("/te2")
    public String te2(Model model){
        model.addAttribute("test",10);
        return "test";
    }
    @GetMapping("/te/{id}/{order}")
    public String te(@PathVariable int id,
                     @PathVariable int order){
       System.out.println(id+","+order);
        return "test2";
    }
    @GetMapping("/te3")
    public String te3(Model model){
        ArrayList<String>list=new ArrayList<String>();
        list.add("data");
        list.add("damage");
        list.add("cake");
        model.addAttribute("list",list);
        return "test";
    }
    @GetMapping("/te4")
    public String te4(Model model){
        ArrayList<Te>list=new ArrayList<Te>();
//        list.add(new Te())
        model.addAttribute("list",list);
        return "test";
    }
    @GetMapping("/te5")
    public String te5(){
        Search search=new Search();
        search.setKeyword("xd");
        searchService.save(search);
        userService.saveSearch(1L,search);
        return "test";
    }
    @GetMapping("/te6/{id}")
    public String te6(@PathVariable int id){
        System.out.println(id);
        return "test";
    }
    @PostMapping("/r")
    public String te7(){
        return "redirect:/te6/"+2;
    }
    @GetMapping("/r2")
    public String te8(){
        Posting posting=postingRepository.findById(3L).orElse(null);
        posting.setLoveNumber(1);
        postingRepository.save(posting);
        return "redirect:/te6/"+2;
    }
    @GetMapping("/r3")
    public String te9(){
        System.out.println(postingRepository.findTotalLikeNumber(15L));
        return "success";
    }
    @GetMapping("/r4")
    public String te10(Model model){
        model.addAttribute("posting",postingRepository.findById(5L).orElse(null));
        return "test2";
    }







}
