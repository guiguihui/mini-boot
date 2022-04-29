import { request } from '../../request/index'

const app = getApp()

Page({
  data: {
   user: {}
  },
  onShow: function(e) {
    const user = wx.getStorageSync('user')
    if (user.id) {
      request({
        url: '/user/' + user.id ,
        method: 'GET'
      }).then(res => {
        this.setData({
          user: res
        })
      })
    } else {
      wx.switchTab({
        url: '../login/login',
      })
    }
  }

})
