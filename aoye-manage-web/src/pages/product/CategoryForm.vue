<template>
  <v-form v-model="valid" ref="myCategoryForm">
    <v-text-field v-model="category.cname" label="请输入分类名称" required :rules="nameRules"/>
    <v-layout class="my-4" row>
      <v-spacer/>
      <v-btn @click="submit" color="primary">提交</v-btn>
      <v-btn @click="clear">重置</v-btn>
    </v-layout>
  </v-form>
</template>

<script>
  export default {
    name: "category-form",
    props: {
      oldCategory: {
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
        category: {
          cname: '' // 分类名称
        },
        nameRules: [
          v => !!v || "分类名称不能为空",
          v => v.length > 1 || "分类名称至少2位"
        ]
      }
    },
    methods: {
      submit() {
        // 表单校验
        if (this.$refs.myCategoryForm.validate()) {
          // 定义一个请求参数对象，通过解构表达式来获取category中的属性
          const params = this.category;
          this.$http({
            method: this.isEdit ? 'put' : 'post',
            url: '/product/category',
            data: this.$qs.stringify(params)
          }).then(() => {
            // 关闭窗口
            this.$emit("close");
            this.$message.success("保存成功！");
            this.$refs.myCategoryForm.reset();
          })
            .catch(() => {
              this.$message.error("保存失败！");
            });
        }
      },
      clear() {
        // 重置表单
        this.$refs.myCategoryForm.reset();
      }
    },
    watch: {
      oldCategory: {// 监控oldCategory的变化
        handler(val) {
          if (val) {
            // 注意不要直接复制，否则这边的修改会影响到父组件的数据，copy属性即可
            this.category = Object.deepCopy(val)
          } else {
            // 为空category
            this.category = {
              cname: ''
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
