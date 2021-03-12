import instance from '@/http/request'
import urls from "@/http/urls";

export { getTimeline, getBlogInfo }
function getBlogInfo() {
    return instance.get(urls.info).then(res => res.data);
}

function getTimeline() {
    return instance.get(urls.timeline).then(res => res.data);
}
