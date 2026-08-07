<script setup>
import { ref } from "vue";

const user = ref({
  name: '',
  gender: '',
  address: '',
})

const onSubmit = () => {
  console.log(user.value);
}

const dialogTableVisible = ref(false)

  const tableData= [
      {date: '2016-05-02',name: '王小虎',address: '上海市普陀区金沙江路 1518 弄'}, 
      {date: '2016-05-04',name: '王小虎',address: '上海市普陀区金沙江路 1517 弄'},
      {date: '2016-05-01',name: '王小虎',address: '上海市普陀区金沙江路 1519 弄'},
      {date: '2016-05-03',name: '王小虎',address: '上海市普陀区金沙江路 1516 弄'}]

const currentPage1 = ref(5)
const currentPage2 = ref(5)
const currentPage3 = ref(5)
const currentPage4 = ref(4)
const pageSize2 = ref(100)
const pageSize3 = ref(100)
const pageSize4 = ref(100)

const background = ref(false)
const disabled = ref(false)

const handleSizeChange = (val) => {
  console.log(`${val} items per page`)
}
const handleCurrentChange = (val) => {
  console.log(`current page: ${val}`)
}
      

</script>
<!--按钮 -->

<template>
  <el-row class="mb-4">
  <el-button>默认按钮</el-button>
  <el-button type="primary">主要按钮</el-button>
  <el-button type="success">成功按钮</el-button>
  <el-button type="info">信息按钮</el-button>
  <el-button type="warning">警告按钮</el-button>
  <el-button type="danger">危险按钮</el-button>
</el-row>

<el-row class="mb-4">
  <el-button plain>朴素按钮</el-button>
  <el-button type="primary" plain>主要按钮</el-button>
  <el-button type="success" plain>成功按钮</el-button>
  <el-button type="info" plain>信息按钮</el-button>
  <el-button type="warning" plain>警告按钮</el-button>
  <el-button type="danger" plain>危险按钮</el-button>
</el-row>

<!-- 表格 -->
<div class = "mb-4">
  <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      prop="date"
      label="日期"
      width="180">
    </el-table-column>
    <el-table-column
      prop="name"
      label="姓名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="address"
      label="地址">
    </el-table-column>
  </el-table>

</div>
<!-- 分页条 -->
 <div class = "mb-4">
   <el-pagination
      v-model:current-page="currentPage4"
      v-model:page-size="pageSize4"
      :page-sizes="[100, 200, 300, 400]"
      :disabled="disabled"
      :background="background"
      layout="total, sizes, prev, pager, next, jumper"
      :total="400"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      />
 </div>

 <!-- 对话框 -->
  <div class="mb-4">
    <el-button class="!ml-0" plain @click="dialogTableVisible = true">
      打开Dialog对话框
    </el-button>

    <el-dialog v-model="dialogTableVisible" title="Shipping address" width="800px">
    <el-table :data="tableData">
      <el-table-column property="date" label="日期" width="150" />
      <el-table-column property="name" label="名字" width="200" />
      <el-table-column property="address" label="地址" />
    </el-table>
  </el-dialog>

  </div>

  <!-- 表单组件 -->
   <div class="mb-4">
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
    <el-form-item label="姓名">
      <el-input v-model="user.name" placeholder="请输入姓名" clearable />
    </el-form-item>
    <el-form-item label="性别">
      <el-select
        v-model="user.gender"
        placeholder="请选择"
        clearable
      >
        <el-option label="男" value="1" />
        <el-option label="女" value="2" />
      </el-select>
    </el-form-item>
    <el-form-item label="生日">
      <el-date-picker
        v-model="user.address"
        type="date"
        placeholder="请选择"
        clearable
        value-format="YYYY-MM-DD"
      />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">Query</el-button>
    </el-form-item>
  </el-form>
   </div>

   <div class="common-layout">
    <el-container>
      <el-aside width="200px">Aside</el-aside>
      <el-container>
        <el-header>Header</el-header>
        <el-main>Main</el-main>
        <el-footer>Footer</el-footer>
      </el-container>
    </el-container>
  </div>

  <el-row :gutter="20">
    <el-col :span="6"><div class="grid-content ep-bg-purple" /></el-col>
    <el-col :span="6"><div class="grid-content ep-bg-purple" /></el-col>
    <el-col :span="6"><div class="grid-content ep-bg-purple" /></el-col>
    <el-col :span="6"><div class="grid-content ep-bg-purple" /></el-col>
  </el-row>

</template>



<style scoped>
.mb-4 {
    margin-bottom: 10px;
}

.demo-form-inline .el-input {
  --el-input-width: 220px;
}

.demo-form-inline .el-select {
  --el-select-width: 220px;
}

.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
  background: #772ed1;
}
</style>
