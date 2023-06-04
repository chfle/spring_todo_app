package org.lehnert.todo.controller;

import jakarta.persistence.AccessType;
import lombok.AllArgsConstructor;
import org.lehnert.todo.database.enums.AccessTypes;
import org.lehnert.todo.database.repository.ListRepository;
import org.lehnert.todo.database.repository.UserRepository;
import org.lehnert.todo.database.tables.Lists;
import org.lehnert.todo.database.tables.UserList;
import org.lehnert.todo.database.tables.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class MainPageController {
    @Autowired
    private final ListRepository listRepository;

    @Autowired
    private final UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(MainPageController.class);
    @GetMapping
    String getMainPage(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());

        return "mainPage";
    }

    /**
     * Add a new list for the current user
     * @return true or false
     */
    @ResponseBody
    @PostMapping("/list")
    Boolean postList(@RequestBody Map<String, Object> params, Authentication authentication) {
        Optional< Users> optionalUsers = userRepository.findUserByUsername(authentication.getName());

        if (optionalUsers.isPresent()) {
            try {
                String listName = (String)params.get("name");
                String accessType = (String)params.get("access");

                Lists lists = new Lists();

                lists.setName(listName);
                lists.setUsers(new ArrayList<>(List.of(optionalUsers.get())));
                lists.setOwner(optionalUsers.get().getId());
                lists.setAccessType(AccessTypes.valueOf(accessType));

                listRepository.save(lists);
                return true;
            } catch (Exception exception) {
                log.error(exception.getMessage());
            }
        }

        return false;
    }
}
