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
// }
