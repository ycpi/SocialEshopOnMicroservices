package com.socialeshop.identification.controllers;

import com.socialeshop.identification.models.ERole;
import com.socialeshop.identification.models.Role;
import com.socialeshop.identification.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInitializer implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        roleRepository.saveAndFlush(new Role(ERole.normal));
        roleRepository.saveAndFlush(new Role(ERole.business));
        roleRepository.saveAndFlush(new Role(ERole.admin));
    }
}
