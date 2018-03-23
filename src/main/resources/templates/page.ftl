<div class="pagination">
    <ul>
    <#if pb??>
        <li>

            <#if (pb.currentPage>1) >
                <a href="/news/queryz/1">首页</a>
            <#else>
                <span>首页</span>
            </#if>
            <#if (pb.currentPage==1) >
                <span>前一页</span>
            <#else>
                <a href="/news/queryz/${pb.currentPage-1}">前一页</a>
            </#if>

        </li>



        <li>
            <#list 1..pb.maxPage as i>
                <a href="/news/queryz/${i}">${i}</a>

            </#list>
        </li>
        <li>
            <#if (pb.currentPage=pb.maxPage) >
                <span>下一页</span>
            <#else>
                <a href="/news/queryz/${pb.currentPage+1}">下一页</a>
            </#if>
        </li>




        <li>

        <#--<a href="#">下一页</a>-->

            <#if (pb.currentPage=pb.maxPage) >
                <span>尾页</span>
            <#else>
                <a href="/news/queryz/${pb.maxPage}">尾页</a>

            </#if>



        </li>

    </#if>
    </ul>

</div>