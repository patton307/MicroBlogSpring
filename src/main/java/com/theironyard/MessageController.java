package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    MessageRepository messages;

    // Home
    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        model.addAttribute("message", messages.findAll());

        return "home";
    }

    // NO USER - CREATE USER
    @RequestMapping("/login")
    public String createUser(String username, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        return "redirect:/";
    }


    // INSERT MESSAGE
    @RequestMapping("/create-message")
    public String createMessage (String text) {
        Message m = new Message();
        m.text = text;

        messages.save(m);
        return "redirect:/";
    }

    // DELETE MESSAGE
    @RequestMapping("/delete-message")
    public String deleteMessage (Integer id) {

        messages.delete(id);

        return "redirect:/";
    }

    // EDIT MESSAGE
    @RequestMapping("/edit")
    public String edit(Integer id, Model model) {

        Message m = messages.findOne(id);

        model.addAttribute("message", m);
        return "edit";
    }

    @RequestMapping("/edit-message")
    public String editMessage(Integer id, String text) {
        Message m = messages.findOne(id);
        if (m != null) {
            m.text = text;
        }
        messages.save(m);
        return "redirect:/";
    }


}
