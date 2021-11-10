import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
    mode: "history",
    routes: [
        {
            path: "/",
            name: "intro",
            component: () => import("./components/Intro")
        },
        {
            path: "/dashboard",
            name: "dashboard",
            component: () => import("./views/Dashboard")
        },
    ]
});