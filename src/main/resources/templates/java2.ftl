public class ${className}{
    <#list table?keys as k>
        private ${table[k].type} ${table[k].name};
    </#list>

}