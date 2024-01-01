<template>
  <div>
    <div style="margin-bottom: 15px;">
      <el-input v-model="input" style="width:250px" placeholder="請輸入關鍵字"></el-input>
      <el-button type="success" style="margin-left:10px" @click="findBySearch()">查詢</el-button>
      <el-button type="info" style="margin-left:10px" @click="clear()">清除</el-button>
      <el-button type="primary" @click="addUser()">新增</el-button>
    </div>
    <el-table :data="usersData" style="width: 100%">
      <el-table-column prop="id" label="編號" width="100" />
      <el-table-column prop="name" label="名稱" width="180" />
      <el-table-column prop="username" label="帳號" width="180" />
      <el-table-column prop="email" label="信箱" width="180" />
      <el-table-column prop="gender" label="性別" width="100" />
      <el-table-column prop="telphone" label="電話" width="180" />
      <el-table-column label="">
        <template v-slot="scope">
          <el-button type="primary" @click="updateUser(scope.row)">編輯</el-button>
          <el-popconfirm title="確定刪除使用者?" @confirm="deleteUser(scope.row.id)" confirm-button-text="確定"
            cancel-button-text="取消">
            <template #reference>
              <el-button type="danger">刪除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分頁待做 -->


    <div>
      <el-dialog v-model="dialogFormVisible" title="輸入您的資料">
        <el-form :model="form">
          <el-form-item label="帳號" :label-width="formLabelWidth">
            <el-input v-model="form.username" autocomplete="off" style="width: 70%;" />
          </el-form-item>
          <el-form-item label="姓名" :label-width="formLabelWidth">
            <el-input v-model="form.name" autocomplete="off" style="width: 70%;" />
          </el-form-item>
          <el-form-item label="電話" :label-width="formLabelWidth">
            <el-input v-model="form.telphone" autocomplete="off" style="width: 70%;" />
          </el-form-item>
          <el-form-item label="信箱" :label-width="formLabelWidth">
            <el-input v-model="form.email" autocomplete="off" style="width: 70%;" />
          </el-form-item>
          <el-form-item label="性別" :label-width="formLabelWidth">
            <el-radio v-model="form.gender" label="男">男</el-radio>
            <el-radio v-model="form.gender" label="女">女</el-radio>
            <el-radio v-model="form.gender" label="不知道">不知道</el-radio>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取消</el-button>
            <el-button type="primary" @click="submit()">
              送出
            </el-button>
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue';
import axios from 'axios';
import { ElMessage } from 'element-plus'

const dialogFormVisible = ref(false)
const formLabelWidth = '140px'
const form = reactive({
  username: '',
  name: '',
  telphone: '',
  email: '',
  gender: ''
})

const requestData = {
  header: {
    apiCode: '0000',
    userId: '感覺要用父傳子抓到當前使用者id',
  },
  body: form
};



const GET_ALL_USERS_URL = "http://localhost:15593/booknow/bk/user/getUsers";
const FIND_BY_SEARCH_URL = "http://localhost:15593/booknow/bk/user/search"
const SAVE_USER_URL = "http://localhost:15593/booknow/bk/user/save";
const DELETE_USER_URL = "http://localhost:15593/booknow/bk/user/";

//新增使用者
const addUser = async () => {
  form.username = '';
  form.name = '';
  form.telphone = '';
  form.email = '';
  form.gender = '';
  dialogFormVisible.value = true;
}

const submit = async () => {
  const response = axios.post(SAVE_USER_URL, requestData);
  const data = (await response).data;
  if (data.header.resultCode === '0000') {
    ElMessage({
      showClose: true,
      message: '成功更新',
      type: 'success',
    })
    dialogFormVisible.value = false;
    getUsers();
  } else {
    ElMessage({
      showClose: true,
      message: data.header.resultDescription,
      type: 'warning',
    })
  }
}

//修改使用者
const updateUser = async (obj: any) => {
  Object.assign(form, obj);
  dialogFormVisible.value = true;
}

//刪除使用者
const deleteUser = async (id: any) => {
  const response = await axios.delete(DELETE_USER_URL + id);
  if (response.data.header.resultCode === '0000') {
    ElMessage({
      showClose: true,
      message: '成功刪除',
      type: 'success',
    })
    dialogFormVisible.value = false;
    getUsers();
  } else {
    ElMessage({
      showClose: true,
      message: response.data.header.resultDescription,
      type: 'warning',
    })
  }

}


const input = ref('');
//得到所有使用者資訊
const usersData = ref([]);
const getUsers = async () => {
  try {
    const response = await axios.get(GET_ALL_USERS_URL);
    if (response.data.header.resultCode === '0000') {
      usersData.value = response.data.body;
    }
  } catch (error) {
    console.error('error:', error);
  }
};

const findBySearch = async () => {
  try {
    const response = await axios.get(FIND_BY_SEARCH_URL, {
      params: {
        param: input.value,
      },
    });
    if (response.data.header.resultCode === '0000') {
      usersData.value = response.data.body;
    }

  } catch (error) {
    console.error('Error:', error);
  }
};

const clear = async () => {
  input.value = '';
  findBySearch();
}


onMounted(getUsers);

</script>
