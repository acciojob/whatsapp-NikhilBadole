package com.driver;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class WhatsappService {

    WhatsappRepository whatsappRepository = new WhatsappRepository();

    public String createUser(String name, String mobile) throws Exception {
        if(whatsappRepository.getUserMap().containsKey(mobile)){
            throw new Exception("User already exists");
        }
        whatsappRepository.createUser(name, mobile);
        return "SUCCESS";
    }

    public Group createGroup(List<User> users) {
        Group group = new Group();
        if(users.size() == 2){
            group.setName(users.get(1).getName());
        }
        else if(users.size() > 2){
            int groupCount = whatsappRepository.getCustomGroupCount();
            groupCount++;
            whatsappRepository.setCustomGroupCount(groupCount);
            group.setName("Group "+ groupCount);
        }

        group.setNumberOfParticipants(users.size());
        whatsappRepository.createGroup(group, users);
        return group;
    }

    public int createMessage(String content) {
        int msgId = whatsappRepository.getMessageId()+1;
        whatsappRepository.createMessage(content);
        return msgId;
    }

    public int sendMessage(Message message, User sender, Group group) throws Exception {
//        if(!whatsappRepository.getGroupUserMap().containsKey(group)){
//            throw new Exception("Group does not exist");
//        }
//
//        if(!whatsappRepository.getGroupUserMap().get(group).contains(sender)){
//            throw new Exception("You are not allowed to send message");
//        }

        return  whatsappRepository.addMessageToGroup(message, group, sender);
    }

    public String changeAdmin(User approver, User user, Group group) throws Exception {
        if(!whatsappRepository.getGroupUserMap().containsKey(group)){
            throw new Exception("Group does not exist");
        }

        if(!whatsappRepository.getAdminMap().get(group).equals(approver)){
            throw new Exception("Approver does not have rights");
        }

        if(!whatsappRepository.getGroupUserMap().get(group).contains(user)){
            throw new Exception("User is not a participant");
        }

        whatsappRepository.getAdminMap().put(group, user);
        return "SUCCESS";
    }

    public int removeUser(User user) {
        return 0;
    }

    public String findMessage(Date start, Date end, int k) {
        return "";
    }
}
