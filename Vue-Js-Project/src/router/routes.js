import User from "layouts/UserLayout";
import Main from "pages/UserPageMain";
import Corrections from "pages/UserPageCorrections";

const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      { path: "", component: () => import("pages/IndexPage.vue") },
      { path: "/login", component: () => import("pages/LoginPage.vue") },
      { path: "/signup", component: () => import("pages/SignupPage.vue") },
    ],
  },
  {
    path: "/user/:login",
    name: "user",
    component: User,
    children: [
      {
        path: "",
        component: Main,
      },
      {
        path: "corrections",
        component: Corrections,
        children: [
          {
            path: "floor",
            component: () => import("pages/Corrections/FloorPage.vue"),
          },
          {
            path: "auction",
            component: () => import("pages/Corrections/AuctionPage.vue"),
          },
          {
            path: "flatArea",
            component: () => import("pages/Corrections/FlatAreaPage.vue"),
          },
          {
            path: "kitchenArea",
            component: () => import("pages/Corrections/KitchenAreaPage.vue"),
          },
          {
            path: "balcony",
            component: () => import("pages/Corrections/BalconyPage.vue"),
          },
          {
            path: "metro",
            component: () => import("pages/Corrections/MetroPage.vue"),
          },
          {
            path: "condition",
            component: () => import("pages/Corrections/ConditionPage.vue"),
          },
        ],
      },
      { path: "analogues", component: () => import("pages/AnaloguesPage.vue") },
      { path: "pool", component: () => import("pages/PoolPage.vue") },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
];

export default routes;
