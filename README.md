# LunaTV Android TV App

基于 LunaTV Enhanced Edition 后端的安卓电视前端应用。

## 功能特性

- 完整的用户认证系统
- 多源影视搜索与浏览
- IPTV 直播支持
- YouTube 集成
- 网盘搜索
- 短剧功能
- AI 智能推荐
- 弹幕系统
- 收藏与播放记录
- TVBox 兼容模式

## 技术栈

- Kotlin 1.9.22
- Jetpack Compose 1.5.x
- Hilt 依赖注入
- Room 数据库
- Retrofit 网络请求
- ExoPlayer 播放器
- MVVM 架构

## 构建

```bash
# Debug 构建
./gradlew assembleDebug

# Release 构建
./gradlew assembleRelease
```

## 运行

```bash
./gradlew installDebug
```

## 配置

在 `app/build.gradle.kts` 中配置后端 API 地址:

```kotlin
buildConfigField("String", "BASE_URL", "\"https://your-lunatv-backend.com\"")
```

## License

CC BY-NC-SA 4.0
