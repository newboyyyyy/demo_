<template>
    <div class="background-container">
        <div class="login-box" style="margin-right: 10%;">
            <div class="welcome-message">註冊</div>
            <div class="form-container">
                <el-form :model="user" label-width="0">
                    <el-form-item>
                        <el-input v-model="user.name" prefix-icon="el-icon-lock" placeholder="請輸入姓名"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="user.username" prefix-icon="el-icon-user" placeholder="請輸入帳號"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="user.password" prefix-icon="el-icon-lock" placeholder="請輸入密碼"
                            type="password"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="user.telphone" placeholder="請輸入電話"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="user.email" placeholder="請輸入信箱"></el-input>
                    </el-form-item>
                    <el-form-item label="性別">
                        <el-radio v-model="user.gender" label="男">男</el-radio>
                        <el-radio v-model="user.gender" label="女">女</el-radio>
                        <el-radio v-model="user.gender" label="不知道">不知道</el-radio>
                    </el-form-item>
                    <el-form-item>
                        <el-button style="width: 100%;background-color: rgb(73, 69, 69);color: aliceblue;"
                            @click="register()">註冊</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>
  

<script setup lang="ts">
import axios from 'axios';
import { reactive } from 'vue';
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router';
const router = useRouter();
const REGISTER_URL = "http://localhost:15593/booknow/bk/user/register";

const user = reactive({
    username: '',
    name: '',
    telphone: '',
    email: '',
    gender: '',
    password: ''
})
const requestData = {
    header: {
        apiCode: '0000',
        userId: '感覺要用父傳子抓到當前使用者id',
    },
    body: user
};

const register = async () => {
    const response = await axios.post(REGISTER_URL, requestData);
    if (response.data.header.resultCode === '0000') {
        ElMessage({
            showClose: true,
            message: '成功註冊',
            type: 'success',
        });
        router.push("/login");

    } else {
        ElMessage({
            showClose: true,
            message: response.data.header.resultDescription,
            type: 'warning',
        })
    }

}


</script>

<style scoped>
.background-container {
    height: 100vh;
    background-image: url('../assets/back.jpg');
    background-size: cover;
    display: flex;
    justify-content: center;
    align-items: center;
}

.login-container {
    width: 100%;
    height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
}

.login-box {
    width: 400px;
    height: 500px;
    background-color: rgba(166, 175, 190, 0.7);
    border-radius: 10px;
    text-align: center;
}

.welcome-message {
    font-size: 30px;
    line-height: 100px;
    color: #f1f1f4;
}

.form-container {
    height: 300px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}
</style>