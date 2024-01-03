package com.smile.petpat.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")

public class RoleController {

    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(value = "/guest", method = RequestMethod.POST)
    public void guest() {
        System.out.println("guest만 이용");
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void user() {

    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public void admin() {

    }
}
