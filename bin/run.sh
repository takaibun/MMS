#!/usr/bin/env bash
# 添加nginx用户
useradd -r -s /sbin/nologin nginx
chown -R nginx:nginx /var/log/nginx /etc/nginx/conf.d /etc/nginx/sites-enabled /opt/pmm/console

# 启动nginx和pmm后端
nginx -c /etc/nginx/nginx.conf &&
java -jar /opt/pmm/service/pmm.jar