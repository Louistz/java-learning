package com.cheny.algorithm.graph;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) {
        File file = new File("/Users/chenyong/Downloads/hkeys.log");

        try(InputStream in = new FileInputStream(file)){
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(reader);

            List<String> apiList = new ArrayList<>();
            String line = "";
            int lineNum = 0;
            while (StringUtils.isNotBlank(line = bufferedReader.readLine())){
                lineNum ++;
                if(lineNum%2 != 0){
                    apiList.add(line);
                }else if(lineNum > 1){
                    apiList.set(apiList.size()-1,apiList.get(apiList.size()-1) + "#"+line);
                }
            }
            if(apiList.isEmpty()){
                return;
            }

            List<Api> apis = new ArrayList<>();
            for(String s : apiList){
                String []cm = s.split("#");
                String className = cm[0];
                String methodName = cm[1];
                long invoke = Long.parseLong(cm[2]);
                apis.add(new Api(className,methodName,invoke));
            }
            apis.sort(new Comparator<Api>() {
                @Override
                public int compare(Api o1, Api o2) {
                    if (!o1.getClassName().equals(o2.getClassName())) {
                        return o1.getClassName().compareTo(o2.getClassName());
                    } else {
                        if (o1.getInvoke() > o2.getInvoke()) {
                            return -1;
                        } else if (o1.getInvoke() < o2.getInvoke()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
            });


            Map<String,List<Api>> apiMap = new TreeMap<>();
            for(Api api : apis){
                if(apiMap.containsKey(api.getClassName())){
                    apiMap.get(api.getClassName()).add(api);
                }else{
                    List<Api> tmp = new ArrayList<>();
                    tmp.add(api);
                    apiMap.put(api.getClassName(),tmp);
                }
            }

            displayTable(apiMap);


        }catch (IOException e){
            e.printStackTrace();
        }




    }

    public static String getSpace(int size){
        if(size <=0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<size;i++){
            sb.append(" ");
        }
        return sb.toString();

    }

    public static void displayTable(Map<String,List<Api>> apiMap){
        StringBuilder sb = new StringBuilder();
        sb.append("<table>")
                .append("<thread>")
                .append("<tr>")
                    .append("<th>ClassName</th>")
                    .append("<th>MethodName</th>")
                    .append("<th>InvokeTimes</th>")
                .append("</tr>")
                .append("</thread>");
        sb.append("<tbody>");
        for(String className : apiMap.keySet()){
            sb.append("<tr>")
                    .append("<td>").append(className).append("</td>")
                    .append("<td>").append("").append("</td>")
                    .append("<td>").append("").append("</td>")
              .append("</tr>");
            for(Api api : apiMap.get(className)){
                sb.append("<tr>")
                        .append("<td>").append("").append("</td>")
                        .append("<td>").append(api.getMethodName()).append("</td>")
                        .append("<td>").append(api.getInvoke()).append("</td>")
                  .append("</tr>");
            }

        }
        sb.append("</tbody>").append("</table>");

        System.out.println(sb.toString());

    }

    public static void displayTxt(Map<String,List<Api>> apiMap){
        int maxLength = 0;
        int maxMethodLength = 0;
        for(String key : apiMap.keySet()){
            if(key.length() > maxLength){
                maxLength = key.length();
            }
            for(Api a : apiMap.get(key)){
                if(a.getMethodName().length() > maxMethodLength){
                    maxMethodLength = a.getMethodName().length();
                }
            }

        }
        maxMethodLength += 4;
        String space = getSpace(maxLength);
        StringBuilder sb = new StringBuilder();
        sb.append("api list : total ").append(apiMap.size()).append("  \n");
        sb.append("--------------------------------------------------------------------------------\n");
        for(String className : apiMap.keySet()){
            sb.append(className).append(":").append("\n");
            for(Api a : apiMap.get(className)){
                sb.append(space).append("  ")
                        .append(a.getMethodName())
                        .append(getSpace(maxMethodLength - a.getMethodName().length()))
                        .append(a.getInvoke())
                        .append("\n");
            }
            sb.append("-------------------------------------------------------------------------\n");
        }
        System.out.println(sb.toString());

    }
}
