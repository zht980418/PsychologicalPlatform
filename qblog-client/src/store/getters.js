const getters = {
    token: state => state.user.token,
    avatar: state => state.user.avatar,
    name: state => state.user.name,
    id: state => state.user.id,
    phoneNumber: state => state.user.phoneNumber,
    user: state => state.user,
}
export default getters
