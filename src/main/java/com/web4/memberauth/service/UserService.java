package com.web4.memberauth.service;

import com.web4.memberauth.domain.User;
import com.web4.memberauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void join(User user){
        user.setRole("USER"); //권한은 USER
        userRepository.save(user); //
    }

    //db에 가서 데이터 조회
    public User loginUser(String id, String pw){
        User userInfo = userRepository.selectUserInfo(id,pw);
        return userInfo;
    }
}
