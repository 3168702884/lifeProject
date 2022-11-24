# umi project

## Getting Started

## Getting Started err
yarn add --dev electron网络错误
原文：
ou can use environment variables to override the base URL, the path at which to look for Electron binaries, and the binary filename. The URL used by @electron/get is composed as follows:
解决方法：
yarn config set electron_mirror https://npmmirror.com/mirrors/electron/
或
npm config set ELECTRON_MIRROR https://npmmirror.com/mirrors/electron/
或
配置 npmrc
在项目根目录新建 .npmrc，内容：
electron_mirror=https://npmmirror.com/mirrors/electron/
