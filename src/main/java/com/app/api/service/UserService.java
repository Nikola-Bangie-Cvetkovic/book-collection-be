package com.app.api.service;

import com.app.api.dao.UserDao;
import com.app.api.entity.User;
import com.app.api.entity.request.RequestUser;
import com.app.api.entity.response.ResponseEntity;
import com.app.api.entity.response.ResponseUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public ResponseEntity addUser(RequestUser user) {
        ResponseEntity responseEntity;
        //id = null;

        //username samo slova , brojevi ili razmak
        String username = user.getUsername();
        List<String> msg = new ArrayList<>();
        if (!user.getRepeatPassword().equals(user.getPassword())) {
            msg.add("PASSWORDS DO NOT MATCH");
        }
        //(?=.*\\S)[a-zA-Z0-9\\s]* ovo je valjda i za space
        if (username.matches("^.{5,}[a-zA-Z0-9\\s]*")) {
            //proveri da li postoji takav u bazi jer username je unikat
            if (userDao.existsUser(user.getUsername()) == 1) {
                msg.add("CHOOSE ANOTHER USERNAME");
            }
        } else {
            msg.add("INVALID USERNAME");
        }
        //password mora da ima min 8 char 1.A,1.a,1#
        String password = user.getPassword();
        //.{8,}(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])
        if (password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*.{8,}")) {
            user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        } else {
            msg.add("INVALID PASSWORD");
        }
        if (msg.isEmpty()) {
            User dbUser = new User();
            dbUser.setId(null);
            dbUser.setUsername(user.getUsername());
            dbUser.setPassword(user.getPassword());
            dbUser = userDao.save(dbUser);
            msg.add("200 OK");
            responseEntity = new ResponseUser(dbUser.getId(), msg, dbUser.getUsername(), dbUser.getBooks());
        } else {
            responseEntity = new ResponseEntity(-1, msg);
        }
        return responseEntity;
    }

    public ResponseEntity getUser(RequestUser user) {
        List<String> msg = new ArrayList<>();
        ResponseEntity responseEntity;
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        User dbUser = userDao.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (dbUser != null) {
            msg.add("200 OK");
            responseEntity = new ResponseUser(dbUser.getId(), msg, dbUser.getUsername(), dbUser.getBooks());
        } else {
            msg.add("NO SUCH USER");
            responseEntity = new ResponseEntity(-1, msg);
        }
        return responseEntity;
    }

    public ResponseEntity editUser(RequestUser user) {
        ResponseEntity responseEntity;
        List<String> msg = new ArrayList<>();
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        User dbUser = userDao.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (dbUser != null) {
            if (user.getNewUsername() != null) {
                if (dbUser.getUsername().equals(user.getNewUsername())) {
                    msg.add("SAME USERNAME");
                } else {
                    if (user.getNewUsername().matches("^.{5,}[a-zA-Z0-9\\s]*")) {
                        //proveri da li postoji takav u bazi jer username je unikat
                        if (userDao.existsUser(user.getNewUsername()) != 0) {
                            msg.add("CHOOSE ANOTHER USERNAME");
                        } else {
                            dbUser.setUsername(user.getNewUsername());
                        }
                    } else {
                        msg.add("INVALID USERNAME");
                    }
                }
            }
            if (user.getNewPassword() != null && user.getRepeatPassword() != null) {
                if (dbUser.getPassword().equals(DigestUtils.sha256Hex(user.getNewPassword()))) {
                    msg.add("SAME PASSWORD");
                } else if (!user.getNewPassword().equals(user.getRepeatPassword())) {
                    msg.add("NOT MATCHING PASSWORDS" + user.getNewPassword() + ", " + user.getRepeatPassword());
                } else {
                    if (user.getNewPassword().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*.{8,}")) {
                        dbUser.setPassword(DigestUtils.sha256Hex(user.getNewPassword()));
                    } else {
                        msg.add("INVALID PASSWORD");
                    }
                }

            }
            if (msg.isEmpty() && ((user.getRepeatPassword() != null && user.getNewPassword() != null) || user.getNewUsername() != null)) {
                dbUser = userDao.save(dbUser);
                msg.add("200 OK");
                responseEntity = new ResponseUser(0, msg, dbUser.getUsername(), dbUser.getBooks());
            } else {
                msg.add("BAD REQUEST");
                responseEntity = new ResponseEntity(-1, msg);
            }
        } else {
            msg.add("INVALID USERNAME AND/OR PASSWORD");
            responseEntity = new ResponseEntity(-1, msg);
        }
        return responseEntity;
    }

    public ResponseEntity deleteUser(RequestUser user) {
        ResponseEntity responseEntity;
        List<String> msg = new ArrayList<>();
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        User dbUser = userDao.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (dbUser != null) {
                userDao.delete(dbUser);
                msg.add("200 OK");
                responseEntity = new ResponseEntity(dbUser.getId(), msg);
        } else {
            msg.add("NO SUCH USER FOUND");
            responseEntity = new ResponseEntity(-1, msg);
        }
        return responseEntity;
    }
}