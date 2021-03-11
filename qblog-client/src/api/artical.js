import instance from '@/http/request'
import urls from "@/http/urls";

export { getArticles}
function getArticles(page, limit) {
    return instance.get(urls.articles, {
        params: {
            page: page,
            limit: limit
        }
    }).then(res => res.data);
}
//     getArticleByID(id) {
//         return instance.get(urls.article + "/" + id).then(res => res.data);
//     },
//     getAllTags() {
//         return instance.get(urls.tags).then(res => res.data);
//     },
//     getArticlesByTag(tag, page, limit) {
//         return instance.get(urls.tag + "/" + tag, {
//             params: {
//                 page: page,
//                 limit: limit
//             }
//         }).then(res => res.data);
//     },
//     getAllCategories() {
//         return instance.get(urls.categories).then(res => res.data);
//     },
//     getArticlesByCategory(category, page, limit) {
//         return instance.get(urls.category + "/" + category, {
//             params: {
//                 page: page,
//                 limit: limit
//             }
//         }).then(res => res.data);
//     },
//     getBlogInfo() {
//         return instance.get(urls.info).then(res => res.data);
//     },
//     getTimeline() {
//         return instance.get(urls.timeline).then(res => res.data);
//     },
//     getMessages(page, limit) {
//         return instance.get(urls.messages, {
//             params: {
//                 page: page,
//                 limit: limit
//             }
//         }).then(res => res.data);
//     },
//     postMessages(message) {
//         return instance.post(urls.messages, message).then(res => res.data);
//     },
//
//     /**
//      * 获取医生预约限制条件
//      * @param {doctorId:number} doctorId 医生id
//      */
//     async getConstraintById(doctorId) {
//         return instance.get(urls.doctordate + '/' + doctorId).then(res => res.data)
//     },
//
//     /**
//      * 获取医生预约日程表
//      * 返回 orderid title【name】 start end uid
//      * @param {doctorId:number} doctorId 医生id
//      */
//     getCalendarById(doctorId) {
//         return instance.get(urls.doctorCalendar + '/' + doctorId).then(res => res.data)
//     },
//
//     /**
//      * 添加预约
//      * @param {*} form 预约信息
//      */
//     postOrder(form) {
//         console.log(form)
//         return instance.post(urls.order, form).then(res => res.data)
//     },
//
//     /**
//      * 获取预约信息【form】
//      * @param {orderId:number} orderId 预约id
//      */
//     getOrderById(orderId) {
//         return instance.get(urls.order + '/' + orderId).then(res => res.data)
//     },
//
//     /**
//      * 删除预约信息【form】
//      * @param {orderId:number} orderId 预约id
//      */
//     deleteOrderById(orderId) {
//         return instance.delete(urls.order + '/' + orderId).then(res => res.data)
//     },
//
//     /**
//      * 修改预约信息
//      * @param {orderId:number} orderId 预约id
//      */
//     updateOrderById(orderId, form) {
//         return instance.put(urls.order + '/' + orderId, form).then(res => res.data)
//     }
// }
