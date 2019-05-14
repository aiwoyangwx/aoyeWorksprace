<template>
  <v-form v-model="valid" ref="myBrandForm">
    <v-select
      v-model="runningMachine.cid"
      :items="items"
      item-text="cname"
      item-value="id"
      label="请选择跑步机所属分类"
      outline
      required :rules="rules"
    ></v-select>
    <!--<v-text-field v-model="runningMachine.cid" label="请输入分类id" />-->
    <v-text-field v-model="runningMachine.currentVersionCode" label="请输入版本号" />
    <v-text-field v-model="runningMachine.customerLevel" label="请输入客户级别" />
    <v-text-field v-model="runningMachine.runtimeMileage" label="请输入运行里程（公里）" />
    <v-text-field v-model="runningMachine.country" label="请输入国家" />
    <v-text-field v-model="runningMachine.province" label="请输入省份" />
    <v-text-field v-model="runningMachine.address" label="请输入地址" />

    <v-layout class="my-4" row>
      <v-spacer/>
      <v-btn @click="submit" color="primary">新增提交</v-btn>
      <v-btn @click="clear">重置</v-btn>
    </v-layout>
  </v-form>
</template>

<script>
  export default {
    name: "RunningMachine-AddForm",
    props: {
      oldrunningMachine: {
        type: Object
      },
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        valid: false, // 表单校验结果标记
        runningMachine: {
          cid: '', // 分类id
          currentVersionCode: '', //版本号
          customerLevel:'',//客户级别
          runtimeMileage:'',//运行里程（公里）
          country:'',//国家
          province:'',//省份
          address:''//地址

        },
        rules: [
          v => !!v || "数据不能为空"//,
          //v => v.length > 1 || "分类名称至少2位"
        ],
        items: []
      }
    },
    mounted() { // 渲染后执行
      // 查询数据
      this.getCategoriesFromServer();
    },
    methods: {
      getCategoriesFromServer() { // 从服务的加载数的方法。
        // 发起请求
        this.$http.get("/product/category/all"
        ).then(resp => { // 这里使用箭头函数
          this.items = resp.data;
        })
      },
      submit() {
        // 表单校验
        if (this.$refs.myBrandForm.validate()) {
          // 定义一个请求参数对象，通过解构表达式来获取brand中的属性
          const params = this.runningMachine;
          this.$http({
            method:'post',
            url: '/product/runningmachine',
            data: this.$qs.stringify(params)
          }).then(() => {
            // 关闭窗口
            this.$emit("close");
            this.$message.success("保存成功！");
            this.$refs.myBrandForm.reset();
          }).catch(() => {
              this.$message.error("保存失败！");
            });
        }
      },
      clear() {
        // 重置表单
        this.$refs.myBrandForm.reset();
      }
    },
    watch: {
      oldrunningMachine: {// 监控oldBrand的变化
        handler(val) {
          if (val) {
            // 注意不要直接复制，否则这边的修改会影响到父组件的数据，copy属性即可
            this.runningMachine = Object.deepCopy(val)
          } else {
            // 为空，初始化brand
            this.runningMachine = {
              cid: '', // 分类id
              currentVersionCode: '', //版本号
              customerLevel:'',//客户级别
              runtimeMileage:'',//运行里程（公里）
              country:'',//国家
              province:'',//省份
              address:''//地址
            }
          }
        },
        deep: true
      }
    }
  }
</script>

<style scoped>

</style>
