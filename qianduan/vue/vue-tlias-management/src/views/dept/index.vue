<script setup>
import { ref,onMounted } from "vue";
import { queryAllApi,addApi,queryByIdApi,updateApi,deleteByIdApi } from "@/api/dept";
import { ElMessage,ElMessageBox } from 'element-plus';


//钩子函数
onMounted(() => {
  search();
})


//查询
const search = async () => {
  const request = await queryAllApi();
  if(request.code){
    deptList.value = request.data;
  }
}

const deptList = ref([])

//Dialog对话框
const dialogFormVisible = ref(false);

const dept = ref({name: ''})



const formTitle = ref('');
const addDept = () => {
  dialogFormVisible.value = true;
  formTitle.value = '新增部门';
  dept.value = {name: ''}
  if(deptFormRef.value){
    deptFormRef.value.resetFields();
  }
  
}

const save = async () => {
  //表单校验
  if(!deptFormRef.value)return;
    deptFormRef.value.validate(async (valid) => {
      let request;
    if(valid){
      if(dept.value.id){
        result = await updateApi(dept.value);
      }else{
        //新增
        result = await addApi(dept.value);
      }
      if(result.code){
        //提示信息
        ElMessage.success('操作成功')
        //关闭对话框
        dialogFormVisible.value = false;
        //查询
        search();
      }else{
        ElMessage.error(result.msg);
      }
    }else{
      
      ElMessage.error('请填写正确的数据')
    }
  })

  
}

//表单校验
const rules = ref({
  name: [
    { required: true, message: '部门名称是必填项', trigger: 'blur' },
    { min: 2, max: 10, message: '部门名称长度应该在2到10', trigger: 'blur' }
  ]
})

const deptFormRef = ref();


//编辑
const edit = async (id) => {
  const result = await queryByIdApi(id);
  if(result.code){
    if(deptFormRef.value){
      deptFormRef.value.resetFields();

    }
    dialogFormVisible.value = true;
    dept.value = result.data;
  }
}

//删除
const delById = async (id) => {
  //弹出确认框
  ElMessageBox.confirm('此操作将永久删除该部门, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    const result = await deleteByIdApi(id);
    if(result.code){
      ElMessage.success('删除成功');
      search();
    }else{
      ElMessage.error(result.message);
    }
  }).catch(() => {
    ElMessage.info('已取消删除');
  })
}

</script>

<template>
  <h1>部门管理</h1>
  <div class="container">
    <el-button type="primary" @click="addDept">新增部门</el-button>
  </div>

  <div class="container">
    <el-table :data="deptList" border style="width: 100%">
    <el-table-column type="index" label="序号" width="100" align="center"/>
    <el-table-column prop="name" label="部门名称" width="260" align="center"/>
    <el-table-column prop="updateTime" label="最后操作时间" width="300" align="center"/>
    <el-table-column label="操作" align="center">
      <template #default="scope">
        <el-button type="primary" size="small" @click="edit(scope.row.id)"><el-icon><EditPen /></el-icon>编辑</el-button>
        <el-button type="primary" size="small" @click="delById(scope.row.id)"><el-icon><Delete /></el-icon>删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  </div>

  <!-- Dialog对话框 -->
    <el-dialog v-model="dialogFormVisible" :title="formTitle" width="500">
    <el-form :model="dept" :rules="rules" ref="deptFormRef">
      <el-form-item label="部门名称" :label-width="80" prop="name">
        <el-input v-model="dept.name" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="save">
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped>
.container {
  margin: 10px 0px;
}
</style>
