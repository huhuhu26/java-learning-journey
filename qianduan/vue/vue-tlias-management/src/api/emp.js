import request from "@/utils/request";

//查询员工列表
export const queryPageApi = (name,gender,begin,end,page,pageSize) => 
    request.get(`/emps?name=${name}&gender=${gender}&begin=${begin}&end=${end}&page=${page}&pageSize=${pageSize}`);


//