package com.cmos.springcloud.controller;

import com.cmos.springcloud.common.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author lizhe
 * @date 2021-05-08 22:09
 */
@RestController
@RequestMapping("/algorithm/")
@Slf4j
public class AlgorithmController {
    @PostMapping("/CollaborativeFiltering")
    public AjaxResult filtering(@RequestBody TreeMap<String,Object> requestMap){
        //请求入参
        Map<String,Object> params = (Map<String,Object>)requestMap.get("params");
        String recommendUser = params.get("user") == null?"":String.valueOf(params.get("user"));

        List userList = new ArrayList();
        userList = (List)params.get("userList");
//        TreeMap<String,Object> userMap = new TreeMap<String,Object>();
        //根据集合长度创建二维数组
        int[][] sparseMatrix = new int[userList.size()][userList.size()];
        //建立用户稀疏矩阵，用于用户相似度计算【相似度矩阵】
        Map<String, Integer> userItemLength = new HashMap<>();
        //存储每一个用户对应的不同物品总数 eg: A 3
        Map<String, Set<String>> itemUserCollection = new HashMap<>();
        //建立物品到用户的倒排表 eg: a A B
        Set<String> items = new HashSet<>();
        //辅助存储物品集合
        Map<String, Integer> userID = new HashMap<>();
        //辅助存储每一个用户的用户ID映射
        Map<Integer, String> idUser = new HashMap<>();
        //辅助存储每一个ID对应的用户映射
        //String user = "";
        String productNameValue = "";
        for (int i = 0; i < userList.size() ; i++){
            Map<String,Object> userMap = (Map<String,Object>)userList.get(i);
            //当前用户id
            String user = userMap.get("userId")==null?"":String.valueOf(userMap.get("userId"));
            productNameValue = userMap.get("productName")==null?"":String.valueOf(userMap.get("productName"));

            //循环历史物品a,b,c 以","间隔
            String[] user_item  = productNameValue.split(",");
            //String user = cfs.get(i).getUserId().toString();
            //物品长度
            int length = user_item.length;
            //key存放user用户id  length存放物品数量
            userItemLength.put(user, length);
            //key存放user用户id  value存放当前用户下标
            userID.put(user, i);
            //用户ID与稀疏矩阵建立对应关系
            idUser.put(i, user);
            //建立物品--用户倒排表
            for (int j = 0; j < length; j ++){
                if(items.contains(user_item[j])){
                    //如果已经包含对应的物品--用户映射，直接添加对应的用户
                    itemUserCollection.get(user_item[j]).add(user);
                } else{
                    //否则创建对应物品--用户集合映射
                    items.add(user_item[j]);
                    itemUserCollection.put(user_item[j], new HashSet<String>());
                    //创建物品--用户倒排关系
                    itemUserCollection.get(user_item[j]).add(user);
                }
            }
        }
        log.info("itemUserCollection:"+itemUserCollection.toString());

        //计算相似度矩阵【稀疏】
        Set<Map.Entry<String, Set<String>>> entrySet = itemUserCollection.entrySet();
        Iterator<Map.Entry<String, Set<String>>> iterator = entrySet.iterator();
        while(iterator.hasNext()){
            Set<String> commonUsers = iterator.next().getValue();
            for (String user_u : commonUsers) {
                for (String user_v : commonUsers) {
                    if(user_u.equals(user_v)){
                        continue;
                    }
                    sparseMatrix[userID.get(user_u)][userID.get(user_v)] += 1;
                    //计算用户u与用户v都有正反馈的物品总数
                }
            }
        }
        log.info("拥有分类数量:"+userItemLength.toString());
        log.info("查询用户下标:"+userID.get(recommendUser));
        //计算用户之间的相似度【余弦相似性】
        Map<String,Object> responseMap = new HashMap<>();
        List resultList = new LinkedList();
        try {
            int recommendUserId = userID.get(recommendUser);
            for (int j = 0;j < sparseMatrix.length; j++) {
                if(j != recommendUserId){
                    log.info(idUser.get(recommendUserId)+"与用户："+idUser.get(j)+"相似度:"+sparseMatrix[recommendUserId][j]/Math.sqrt(userItemLength.get(idUser.get(recommendUserId))*userItemLength.get(idUser.get(j))));
                }
            }
            //存放推荐的大赛
            //计算指定用户recommendUser的物品推荐度
            for (String item: items){
                //遍历每一件物品
                Set<String> users = itemUserCollection.get(item);
                //得到购买当前物品的所有用户集合
                if(!users.contains(recommendUser)){
                    //如果被推荐用户没有购买当前物品，则进行推荐度计算
                    double itemRecommendDegree = 0.0;
                    for (String user: users){
                        itemRecommendDegree += sparseMatrix[userID.get(recommendUser)][userID.get(user)]/Math.sqrt(userItemLength.get(recommendUser)*userItemLength.get(user));
                        //推荐度计算
                    }
                    Map<String,Object> cfMap = new HashMap<>();
                    cfMap.put("name",item);
                    cfMap.put("recommendation",itemRecommendDegree);
                    resultList.add(cfMap);
                    log.info("用户"+recommendUser+"为您推荐"+item+"推荐度为："+itemRecommendDegree);
                }
            }
            responseMap.put("res_code","0");
            responseMap.put("result",resultList);
            return AjaxResult.success(responseMap);
        }catch (Exception e){
            return AjaxResult.error(-9999,e.getMessage());
        }
    }
}
