//package common.rule.themis.builder;
//
//import common.rule.themis.entity.NeedPermission;
//import common.rule.themis.entity.RoleItem;
//import common.rule.themis.entity.User;
//
//public class ItemBuilder {
//    public static ItemTemplate getItemTemplate(){
//        return new ItemTemplate();
//    }
//    public static RoleItem buildRoleItem()
//    public static class ItemTemplate{
//        private String requestId;
//        private NeedPermission needPermission;
//        private User user;
//        public ItemTemplate setRequestId(String requestId){
//            this.requestId = requestId;
//            return this;
//        }
//        public ItemTemplate setUser(User user){
//            this.user = user;
//            return this;
//        }
//        public ItemTemplate setNeedPermission(NeedPermission needPermission){
//            this.needPermission = needPermission;
//            return this;
//        }
//        public RoleItem build(){
//            RoleItem item = new RoleItem(this.requestId,this.needPermission,this.user);
//            return item;
//        }
//    }
//}
