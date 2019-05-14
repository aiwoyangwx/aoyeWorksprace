var menus = [
  {
    action: "home",
    title: "首页",
    path:"/index",
    items: [{ title: "统计", path: "/dashboard" }]
  },
  {
    action: "apps",
    title: "产品管理",
    path:"/product",
    items: [
      { title: "分类管理", path: "/category" },
      { title: "跑步机管理", path: "/runningmachine" },
      { title: "客户端版本", path: "/installer" },
      { title: "升级计划（敬请期待）", path: "" },
      { title: "跑步记录（敬请期待）", path: "" },
    ]
  },
  {
    action: "directions_run",
    title: "私教管理（APP）(敬请期待)",
    path:"",
    items: [
      { title: "简单方案", path: "" },
      { title: "健身月计划", path: "" }
    ]
  },
  {
    action: "local_activity",
    title: "积分管理（APP）(敬请期待)",
    path:"",
    items: [
      { title: "积分商品", path: "" },
      { title: "积分记录查询", path: "" }
    ]
  },
  {
    action: "school",
    title: "推广及知识（APP）(敬请期待)",
    path:"",
    items: [
      { title: "推广活动", path: "" },
      { title: "健身小常识", path: "" }
    ]
  },
  {
    action: "attach_money",
    title: "客户管理(敬请期待)",
    path:"",
    items: [
      { title: "客户列表", path: "" }
    ]
  },

  {
    action: "people",
    title: "员工管理(敬请期待)",
    path:"",
    items: [
      { title: "员工列表", path: "" }
    ]
  },

  {
    action: "settings",
    title: "权限管理(敬请期待)",
    path:"",
    items: [
      { title: "人员设置", path: "" },
      { title: "角色设置", path: "" },
      { title: "权限设置", path: "" }
    ]
  },








  {
    action: "apps",
    title: "商品管理(仅供参考)",
    path:"/item",
    items: [
      { title: "分类管理(仅供参考)", path: "/category" },
      { title: "品牌管理(仅供参考)", path: "/brand" },
      { title: "商品列表(仅供参考)", path: "/list" },
      { title: "规格参数(仅供参考)", path: "/specification" }
    ]
  },
  {
    action: "people",
    title: "会员管理(仅供参考)",
    path:"",
    items: [
      { title: "会员统计(仅供参考)", path: "" },
      { title: "会员管理(仅供参考)", path: "" }
    ]
  },
  {
    action: "attach_money",
    title: "销售管理(仅供参考)",
    path:"",
    items: [
      { title: "交易统计(仅供参考)", path: "" },
      { title: "订单管理(仅供参考)", path: "" },
      { title: "物流管理(仅供参考)", path: "" },
      { title: "促销管理(仅供参考)", path: "" }
    ]
  },
  {
    action: "settings",
    title: "权限管理(仅供参考)",
    path:"",
    items: [
      { title: "权限管理(仅供参考)", path: "" },
      { title: "角色管理(仅供参考)", path: "" },
      { title: "人员管理(仅供参考)", path: "" }
    ]
  }
]

export default menus;
