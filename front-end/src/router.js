import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
    mode: "history",
    routes: [
        {
            path: "/",
            alias: "/intro",
            name: "intro",
            component: () => import("./components/Intro")
        },
        {
            path: "/polls",
            name: "polls",
            component: () => import("./components/Polls")
        },
    ]
});