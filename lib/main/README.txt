Dependencies and other things:
* aws-java-sdk-1.3.27-withoutEmail.jar
  # patched version, based on aws-java-sdk-1.3.27.jar, patched as follows:
    mkdir aws
    cd aws/
    jar xvf ../aws-java-sdk-1.3.27.jar 
    cd META-INF/
    mv javamail.providers javamail.providers.INVALID
    jar cvf ../aws-java-sdk-1.3.27-withoutEmail.jar .
  * commons-codec-1.7.jar

* commons-fileupload-1.2.2.jar depends on:
  * commons-io-2.4.jar

* med_contentimport.jar depends on:
    httpclient-4.2.1.jar
    httpclient-cache-4.2.1.jar
    httpcore-4.2.1.jar
    httpmime-4.2.1.jar
    jdom-1.1.3.jar
    rome-1.0.jar
    scala-library.jar
    tagsoup-1.2.1.jar

* ehcache-2.7.0.jar depends on:
    slf4j-api-1.6.6.jar (but it was already in the lib folder i.e. also used from other lib)
