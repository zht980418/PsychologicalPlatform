import instance from '@/http/request'
import urls from "@/http/urls";

export { getArticles, getArticleByID, getAllTags, getArticlesByTag, getAllCategories, getArticlesByCategory}

function getArticles(page, limit) {
    return instance.get(urls.articles, {
        params: {
            page: page,
            limit: limit
        }
    }).then(res => res.data);
}

function getArticleByID(id) {
    return instance.get(urls.article + "/" + id).then(res => res.data);
}

function getAllTags() {
    return instance.get(urls.tags).then(res => res.data);
}

function getArticlesByTag(tag, page, limit) {
    return instance.get(urls.tag + "/" + tag, {
        params: {
            page: page,
            limit: limit
        }
    }).then(res => res.data);
}

function getAllCategories() {
    return instance.get(urls.categories).then(res => res.data);
}

function getArticlesByCategory(category, page, limit) {
    return instance.get(urls.category + "/" + category, {
        params: {
            page: page,
            limit: limit
        }
    }).then(res => res.data);
}


