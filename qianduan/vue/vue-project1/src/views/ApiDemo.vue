<script setup>
//引入ref函数
import { ref,onMounted } from "vue";
//创建响应式数据

const count = ref(0)

//声明函数
function increment(){
    count.value++;
}

//钩子函数
onMounted(() => {
    console.log('Vue mounted...')
});
</script>

<template>
    <button v-on:click="increment">count: {{ count }}</button>
</template>

<style scoped>

</style>