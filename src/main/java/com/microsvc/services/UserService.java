package com.microsvc.services;

import com.microsvc.entity.User;
import com.microsvc.repository.UserRepository;
import com.microsvc.vo.Department;
import com.microsvc.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside the UserService:: saveUser()::");
        User obj = (User) userRepository.save(user);
        return obj;
    }

    public ResponseVO findByUserId(Long userId) {
        log.info("Inside the UserService:: findByUserId()::");
        ResponseVO responseVO = new ResponseVO();
        User user = (User) userRepository.findById(userId).get();
        responseVO.setUser(user);
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE:9001/department/"
                + user.getDepartmentId(), Department.class);
        responseVO.setDepartment(department);
        return responseVO;
    }
}
