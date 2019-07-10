@echo off
echo "开始生成代码"
java -jar mybatis-generator-core-1.3.2.jar -configfile generator.xml -overwrite
echo "生成代码完毕"