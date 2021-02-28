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

export function RetransForm(form) {
    form['roomid'] = form.roomId
    delete (form['roomId'])
    form['orderid'] = form.orderId
    delete (form['orderId'])
    form['doctorid'] = form.doctorId
    delete (form['doctorId'])
    return form
}

export function newForm() {
    return {
        doctorId: '', //咨询师编号
        orderId: '', // 预约编号
        uid: '', // 来访者id
        type: '', // 线上/线下预约
        ordertime: '', // 预约时间
        name: '', // 来访者姓名
        gender: '', // 性别
        birth: '', // 生日
        occupation: '', // 职业
        phone: '', // 电话
        address: '', // 地址
        emergency: '', // 紧急联系人
        emergencyphone: '', // 紧急联系电话
        question: '', // 来询问题
        family: '', // 家庭情况
        expectation: '', // 咨询期望
        history: '', // 咨询历史
        test: '', // 心理测试
        sleep: '', // 睡眠状况
        relationship: '', // 人际关系
        stress: '', // 压力
        mood: '', // 心情
        hurt: '', // 自伤
        suicide: '', // 自杀
        roomId: '', // 咨询室
        status: '',// 预约接受状态
    }
}
