package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.
    private HashMap<Group, List<User>> groupUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    //private HashMap<Message, User> senderMap;
    private HashMap<Group, User> adminMap;
    //private HashSet<String> userMobile;
    private int customGroupCount;
    private int messageId;

    private HashMap<String, User> userMap;  // mobNo and name of user
    private HashSet<String> messageSet;

    public WhatsappRepository(){
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        //this.senderMap = new HashMap<Message, User>();
        this.adminMap = new HashMap<Group, User>();
        //this.userMobile = new HashSet<>();
        this.customGroupCount = 0;
        this.messageId = 0;

        this.userMap = new HashMap<>();
        this.messageSet = new HashSet<>();
    }

    public HashMap<String, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(HashMap<String, User> userMap) {
        this.userMap = userMap;
    }

    public int getCustomGroupCount() {
        return customGroupCount;
    }

    public void setCustomGroupCount(int customGroupCount) {
        this.customGroupCount = customGroupCount;
    }

    public HashMap<Group, User> getAdminMap() {
        return adminMap;
    }

    public void setAdminMap(HashMap<Group, User> adminMap) {
        this.adminMap = adminMap;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public HashSet<String> getMessageSet() {
        return messageSet;
    }

    public void setMessageSet(HashSet<String> messageSet) {
        this.messageSet = messageSet;
    }

    public HashMap<Group, List<User>> getGroupUserMap() {
        return groupUserMap;
    }

    public void setGroupUserMap(HashMap<Group, List<User>> groupUserMap) {
        this.groupUserMap = groupUserMap;
    }



    public void createUser(String name, String mobile){
        User user = new User(name, mobile);
        userMap.put(mobile,user);
    }


    public void createGroup(Group group, List<User> users) {
        groupUserMap.put(group,users);
        adminMap.put(group, users.get(0));
    }

    public void createMessage(String content) {
        messageSet.add(content);
        messageId++;
    }

    public int addMessageToGroup(Message message, Group group) {
        List<Message> messageInGroup = groupMessageMap.get(group);
        messageInGroup.add(message);
        groupMessageMap.put(group, messageInGroup);
        return messageInGroup.size();
    }
}
