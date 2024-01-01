<template>
    <el-container style="background-color: #687179;">
        <el-header>
            <img src="@/assets/logo.png" style="width: 40px;position: relative;top:10px">
            <span style="font-size: 20px; margin-left: 15px;color: white;">不要鬧 BOOKNOW</span>
        </el-header>
    </el-container>


    <el-container>
        <el-aside style="overflow:hidden; background-color: #545c64; min-height: 100vh;">
            <el-menu :default-active="$route.path" router active-text-color="#ffd04b" background-color="#545c64"
                class="el-menu-vertical-demo" text-color="#fff">
                <el-menu-item index="/">
                    <el-icon><icon-menu /></el-icon>
                    <span>系統首頁</span>
                </el-menu-item>
                <el-menu-item index="/booking">
                    <el-icon><icon-menu /></el-icon>
                    <span>訂位系統</span>
                </el-menu-item>
                <el-sub-menu index="/user">
                    <template #title>
                        <el-icon>
                            <location />
                        </el-icon>
                        <span>會員中心</span>
                    </template>
                    <el-menu-item-group title="">
                        <el-menu-item index="/user">查詢功能</el-menu-item>
                        <el-menu-item index="/update-user">修改你的帳密</el-menu-item>
                    </el-menu-item-group>
                </el-sub-menu>

            </el-menu>
        </el-aside>
        <el-main>
            <router-view />
        </el-main>
    </el-container>
</template>

<style scoped>
.el-menu {
    border-right: none !important;
}
</style>

<script setup></script>