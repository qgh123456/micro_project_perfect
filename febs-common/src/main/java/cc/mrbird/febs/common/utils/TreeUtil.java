package cc.mrbird.febs.common.utils;


import cc.mrbird.febs.common.entity.Tree;
import cc.mrbird.febs.common.entity.route.VueRouter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MrBird
 */
public abstract class TreeUtil {

    private final static String TOP_NODE_ID = "0";

    /**
     * 用于构建菜单或部门树
     *
     * @param nodes nodes
     * @return <T> List<? extends Tree>
     */
    public static <T> List<? extends Tree<?>> build(List<? extends Tree<T>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<Tree<T>> treeList = new ArrayList<>();
        for(Tree<T> item : nodes){
            if(item.getParentId() == null || "0".equals(item.getParentId())){
                treeList.add(item);
            }
            // 找到子
            for(Tree<T> treeNode : nodes){
                if(treeNode.getParentId().equals(item.getId())){
                    if(item.getChildren() == null){
                        item.setChildren(new ArrayList<>());
                    }
                    item.getChildren().add(treeNode);
                }
            }
        }
        return treeList;
    }


    /**
     * 构造前端路由
     *
     * @param routes routes
     * @param <T>    T
     * @return ArrayList<VueRouter < T>>
     */
    public static <T> List<VueRouter<T>> buildVueRouter(List<VueRouter<T>> routes) {
        if (routes == null) {
            return null;
        }
        List<VueRouter<T>> topRoutes = new ArrayList<>();
        VueRouter<T> router = new VueRouter<>();
        routes.forEach(route -> {
            String parentId = route.getParentId();
            if (parentId == null || TOP_NODE_ID.equals(parentId)) {
                topRoutes.add(route);
                return;
            }
            for (VueRouter<T> parent : routes) {
                String id = parent.getId();
                if (id != null && id.equals(parentId)) {
                    if (parent.getChildren() == null) {
                        parent.initChildren();
                    }
                    parent.getChildren().add(route);
                    parent.setAlwaysShow(true);
                    parent.setHasChildren(true);
                    route.setHasParent(true);
                    parent.setHasParent(true);
                    return;
                }
            }
        });
        VueRouter<T> router404 = new VueRouter<>();
        router404.setName("404");
        router404.setComponent("error-page/404");
        router404.setPath("*");

        topRoutes.add(router404);
        return topRoutes;
    }
}