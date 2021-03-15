import instance from '@/http/request'
import urls from '@/http/request'

export{ getMessages, postMessages }
function getMessages(page, limit) {
    return instance.get(urls.messages, {
        params: {
            page: page,
            limit: limit
        }
    }).then(res => res.data);
}

function postMessages(message) {
    return instance.post(urls.messages, message).then(res => res.data);
}

