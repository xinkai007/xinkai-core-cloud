package com.xinkai.common.mybatis.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import com.xinkai.common.core.constant.GlobalConstants;
import com.xinkai.common.mybatis.annotation.DataPermission;
import com.xinkai.common.web.utils.JwtUtils;
import com.xinkai.common.web.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.schema.Column;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @className: com.xinkai.common.mybatis.handler.MyDataPermissionHandler
 * @description: 部门数据权限
 * @author: xinkai
 * @email: xinkai8011@gmail.com
 * @date: 2023/06/23
 **/
@Slf4j
public class MyDataPermissionHandler implements DataPermissionHandler {

    /**
     * 获得sql部分
     *
     * @param where             在哪里
     * @param mappedStatementId 映射语句id
     * @return {@link Expression}
     */
    @Override
    public Expression getSqlSegment(Expression where, String mappedStatementId) {
        try {
            Class<?> clazz = Class.forName(mappedStatementId.substring(0, mappedStatementId.lastIndexOf(".")));
            String methodName = mappedStatementId.substring(mappedStatementId.lastIndexOf(".") + 1);
            clazz.getAnnotatedSuperclass();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                DataPermission annotation = method.getAnnotation(DataPermission.class);
                if (ObjectUtils.isNotEmpty(annotation) && (method.getName().equals(methodName) || (method.getName() + "_COUNT").equals(methodName))) {
                    // 获取当前的用户角色
                    List<String> roles = UserUtils.getRoles();
                    if (!roles.isEmpty() && roles.contains(GlobalConstants.ROOT_ROLE_CODE)) {
                        // 如果是超级管理员则放行
                        return where;
                    } else {
                        return dataScopeFilter(annotation.deptAlias(), where);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return where;
    }

    /**
     * 构建过滤条件
     *
     * @param where     当前查询条件
     * @param deptAlias 部门别名
     * @return 构建后查询条件
     */
    public static Expression dataScopeFilter(String deptAlias, Expression where) {
        Expression expression = new EqualsTo(new Column(StrUtil.isEmpty(deptAlias) ? "id" : deptAlias + ".id"), getDeptId());
        LikeExpression likeExpression = new LikeExpression();
        Function left = new Function();
        left.setName("concat");
        left.setParameters(new ExpressionList().addExpressions(new StringValue(","), new Column("tree_path"), new StringValue(",")));
        likeExpression.setLeftExpression(left);
        Function right = new Function();
        right.setName("concat");
        right.setParameters(new ExpressionList().addExpressions(new StringValue("%,"), getDeptId(), new StringValue("%,")));
        likeExpression.setRightExpression(right);
        expression = ObjectUtils.isNotEmpty(expression) ? new OrExpression(expression, likeExpression) : expression;

        return ObjectUtils.isNotEmpty(where) ? new AndExpression(where, new Parenthesis(expression)) : expression;
    }

    /**
     * 当前用户的部门id
     *
     * @return {@link Expression}
     */
    private static Expression getDeptId() {
        LongValue deptId = new LongValue(JwtUtils.getJwtPayload().getLong("deptId"));
        return deptId;
    }

}

