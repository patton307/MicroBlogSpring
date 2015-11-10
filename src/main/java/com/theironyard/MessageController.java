package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by landonkail on 11/9/15.
 */

@Controller
public class MessageController {

    List<Message> list = new ArrayList<>();

    // Home
    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            model.addAttribute("username", username);
            model.addAttribute("message", list);
        } catch (Exception e) {}
        return "home";
    }


    // NO USER - INITIAL LOGIN
    @RequestMapping("/login")
    public String createUser(HttpServletRequest request, String username) {
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        return "redirect:/";
    }


    // INSERT MESSAGE
    @RequestMapping("/create-message")
    public String createMessage (String text) {
        int id = list.size() + 1;
        Message m = new Message(id, text);
        list.add(m);
        return "redirect:/";
    }

    // DELETE MESSAGE
    @RequestMapping("/delete-message")
    public String deleteMessage (Integer id) {
        list.remove(id -1);
        int idNum = 1;
        for (Message m : list) {
            m.id = idNum;
            idNum++;
        }

        /*list = list.stream()
                .filter(msg -> msg.id == id)
                .collect(Collectors.toList());
        */
        return "redirect:/";
    }
}
