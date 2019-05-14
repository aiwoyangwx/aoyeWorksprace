<template>
  <v-form v-model="valid" ref="myInstallerForm">
    <v-select
      v-model="installer.cid"
      :items="items"
      item-text="cname"
      item-value="id"
      label="请选择版本所属分类"
      outline
      required :rules="rules"
    >
    </v-select>
    <v-text-field v-model="installer.versionCode" label="请输入版本名称" required :rules="rules"/>
    <v-text-field v-model="installer.versionNum" label="请输入版本序号(数字)" required :rules="rules"/>

    <v-layout row>
      <v-flex xs3>
        <span style="font-size: 16px; color: #444">上传客户端：</span>
      </v-flex>
      <v-flex>
       <v-upload
         v-model="installer.fileName" url="/product/installer/upload" :multiple="false"
         :pic-width="150" :pic-height="150" required :rules="rules" ref="myChild"
       />
      </v-flex>
    </v-layout>
    <v-layout class="my-4" row>
      <v-spacer/>
      <v-btn @click="submit" color="primary">新增提交</v-btn>
      <v-btn @click="clear">重置</v-btn>
    </v-layout>
  </v-form>
</template>

<script>
  export default {
    name: "installer-form",
    props: {
      oldInstaller: {
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
        installer: {
          cid: '', // 分类id
          versionCode: '', //版本名称
          versionNum:'',//版本序号
          fileName:''//文件名称
        },
        rules: [
          v => !!v || "数据不能为空"//,
          //v => v.length > 1 || "分类名称至少2位"
        ],
        /*numRule: [
          'installer.versionNum', 'number'
        ],*/
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
        if (this.$refs.myInstallerForm.validate()) {
          // 定义一个请求参数对象，通过解构表达式来获取installer中的属性
          const params = this.installer;
          this.$http({
            method:this.isEdit ? 'put' : 'post',
            url: '/product/installer',
            data: this.$qs.stringify(params)
          }).then(() => {
            // 关闭窗口
            this.$emit("close");
            this.$message.success("保存成功！");
            this.$refs.myInstallerForm.reset();
          }).catch((resp, file) => {
            let message = resp.response.data.message;
            debugger;
            this.$message.error("保存失败！"+message);
          });
        }
      },
      clear() {
        if (this.installer.fileName!="") {
          let urlStr = this.installer.fileName;
          let s = urlStr.substr(51);
          this.$http.delete("/product/installer/url/" +s)
            /*.then(() => {
              this.$message.success("删除成功！");
              this.getDataFromServer();
            })*/
        }
        // 重置表单
        this.$refs.myInstallerForm.reset();
        this.$refs.myChild.removeSingle();
      }
    },
    watch: {
      oldInstaller: {// 监控oldInstaller的变化
        handler(val) {
          if (val) {
            // 注意不要直接复制，否则这边的修改会影响到父组件的数据，copy属性即可
            this.installer = Object.deepCopy(val)
          } else {
            // 为空，初始化installer
            this.installer = {
              cid: '', // 分类id
              versionCode: '', //版本名称
              versionNum:'',//版本序号
              fileName:''//文件名称
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
