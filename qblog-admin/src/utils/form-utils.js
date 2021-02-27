/* eslint-disable */
// 大小写转换
export function transForm(form) {
    // roomid
    form['roomId'] = form.roomid
    delete (form['roomid'])
    // orderid
    form['orderId'] = form.orderid
    delete (form['orderid'])
    // doctorid
    form['doctorId'] = form.doctorid
    delete (form['doctorid'])
    return form
}