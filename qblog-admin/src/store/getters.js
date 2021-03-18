const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  id: state => state.user.id,
  phoneNumber: state => state.user.phoneNumber,
  role: state => state.user.role.split(' '),
  addRouters: state => state.permission.routers,
  user: state => state.user,
}
export default getters
