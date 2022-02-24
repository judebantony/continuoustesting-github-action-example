# Continuous Testing with Synthetic Data using GitHub Action #
[![Main Branch](https://github.com/judebantony/continuoustesting-github-action-example/actions/workflows/workflow.yml/badge.svg)](https://github.com/judebantony/continuoustesting-github-action-example/actions/workflows/workflow.yml)

## Continuous Testing in DevSecOps
Continuous testing in DevSecOps is a type of software testing that involves testing at every stage of the development life cycle. The goal of continuous testing is to evaluate the quality of the software as part of a continuous delivery process, by testing early and often. 

The old way of testing was hand off centric. The software was handed off from one team to another. A project would have definite Development and QA phases. QA teams always wanted more time to ensure quality. The goal was that the quality should prevail over project schedule.

Businesses of today, however, business wants faster delivery of software to the end user. The newer is the software, the better it can be marketed and increase revenue potential of the company. Hence, a new way of testing was evolved. Continuous Testing is a procedure of testing early, testing regular, test everywhere, and automate. Continuous means undisrupted testing done on a continuous basis. In a Continuous DevSecOps process, a software change continuously moves from Development to Testing to Deployment. This process helps us to find the risk, address them and improve the quality of the product.

## Data Driven Testing 

Data-driven testing (DDT), also known as table-driven testing or parameterized testing, is a software testing methodology that is used in the testing of computer software to describe testing done using a table of conditions directly as test inputs and verifiable outputs as well as the process where test environment settings and control are not hard-coded. In the simplest form the tester supplies the inputs from a row in the table and expects the outputs which occur in the same row. The table typically contains values which correspond to boundary or partition input spaces. In the control methodology, test configuration is "read" from a database/csv/excel.

[Cucumber](https://cucumber.io) is an open-source testing framework that supports Behavior Driven Development for automation testing of web applications. The tests are first written in a simple scenario form that describes the expected behavior of the system from the user’s perspective. It explains the behavior of the application in a simple English text using [Gherkin](https://cucumber.io/docs/gherkin/) language.

[Selenium](https://www.selenium.dev) is an open-source umbrella project for a range of tools and libraries aimed at supporting web browser automation. Selenium provides a playback tool for authoring functional tests without the need to learn a test scripting language


## Synthetic Data for Privacy Preservation

Software engineers and data scientists often need access to large volumes of real data to develop, experiment, and innovate. Collecting such data unfortunately also introduces security liabilities and privacy concerns which affect individuals, organizations, and society at large. Data containing Personally Identifiable Information (PII) and Personal Health Information (PHI) are particularly vulnerable to disclosure, and need to be protected.

Regulations such as the General Data Protection Regulation (GDPR) serve to provide a level of legal protection for user data, but consequently introduce new technical challenges by restricting data usage, collection, and storage methods. In light of this, synthetic data could serve as a viable solution to protect user data privacy, stay compliant with regulations, and still maintain the pace and ability for development and innovation.

Synthetic data generation (SDG) is rapidly emerging as a practical privacy enhancing technology (PET) for sharing data for secondary purposes. It does so by generating non-identifiable datasets that can be used and disclosed without the legislative need for additional consent given that these datasets would not be considered personal information.

Privacy engineering expertise delivered to you as easy to use tools and APIs. Synthesize, Classify and Transform your data in minutes. Build trust, and innovate faster for your users. [Gretel](https://gretel.ai/) make it simple to create anonymized and synthetic datasets so you can work safely with data while preserving privacy.

## Create Synthetic Data using Gretel.ai
[Gretel](https://gretel.ai/) has CLI, APIs and web based portal SaaS offering for creating synthetic data. Trained the machine learning models on your datasets and generate synthetic data that statistically equivalent. Automatically label data and perform privacy-preserving transformations on any dataset and automatically label classify your data. 

#### Kaggle - Sample dataset for training
[Kaggle](https://www.kaggle.com/datasets) a subsidiary of Google LLC, is an online community of data scientists and machine learning practitioners. Kaggle allows users to find and publish data sets, explore and build models in a web-based data-science environment, work with other data scientists and machine learning engineers, and enter competitions to solve data science challenges.
 
Sample dataset from Kaggle is present [here](https://github.com/judebantony/continuoustesting-github-action-example/tree/main/doc/dataset.csv) 

Kaggle dashboard:-
![kaggle](./doc/kaggle.png)

#### Gretel.ai - Synthetic data

1) Create a project in Gretel</br>
Gretel dashboard:-
![greteldashboard](./doc/greteldashboard.png)

2) Create a model in Gretel and upload the data set</br>
Create model:-
![gretelcretemodel](./doc/gretelcretemodel.png)

3) Train the ML model with configuration and validate

Train the ML Model:-
![greteltraining](./doc/greteltraining.png)
![greteltrainieddata](./doc/greteltrainieddata.png)
Validate the Model:-
![gretelvalidate](./doc/gretelvalidate.png)
Model Output:-
![gretelmodeloutput](./doc/gretelmodeloutput.png)
Model Download:-
![greteldownload](./doc/greteldownload.png)
Model Report:-
![gretelmodelreport](./doc/gretelmodelreport.png)


## Integrating Continuous Testing tools using GitHub Action ##
Integration & Implementation of Continuous Testing workflow using [Github Action](https://github.com/features/actions), this has been achieved using GitHub Cloud, Gretel & LamdaTest SaaS tools listed below.

### 1) Gretel - Download Synthetic Test Data File from Gretel SaaS###
Download already modeled & trained Synthetic Test Data from [Gretel](https://gretel.ai/) SaaS and uploaded as artifact in GitHub. This data is used for our Functional Testing.

```yaml

  syntheticDataDownload:
    name: Download Synthetic Test Data File from Gretel  
    runs-on: ubuntu-latest
    
    steps:
    - name: Get Synthetic Test Data File
      id: myRequest
      uses: fjogeleit/http-request-action@master
      with:
        url: 'https://api.gretel.cloud/projects/${{env.GRETEL_PROJECT}}/models/${{env.GRETEL_MODEL}}/artifact?type=data_preview'
        method: 'GET'
        customHeaders: '{"Authorization": "${{ secrets.GRETEL_TOKEN }}"}'
    - name: Store the Response
      run: cat <<< ${{ toJSON(steps.myRequest.outputs.response) }} > gretel_out.json
    - name: Read the Respose
      id: format_script
      uses: notiz-dev/github-action-json-property@release
      with: 
          path: 'gretel_out.json'
          prop_path: 'data.url'
    - run: curl -X GET "${{steps.format_script.outputs.prop}}" > data_preview.gz
    - run: gzip -d data_preview.gz
    - run: mv data_preview ${{env.GRETEL_TEST_OUT_FILE}}
    - name: Upload code coverage report
      uses: actions/upload-artifact@v2
      with:
        name: gretel_testdata
        path: ${{env.GRETEL_TEST_OUT_FILE}}

```

GitHub artifact:-
![githubactionartifats](./doc/githubactionartifats.png)


### 2) Maven - Build and Unit Test ###
[Apache Maven](https://maven.apache.org) is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information.

Code is build using [Maven](https://maven.apache.org) and unit test cases are executed using [JUnit](https://junit.org/junit5/)
The test coverage result, which is aggregated by [Jacoco](https://www.baeldung.com/sonarqube-jacoco-code-coverage) would be uploaded to [Github Action](https://github.com/features/actions) as artifacts. Please check [pom.xml](https://github.com/judebantony/continuoustesting-github-action-example/tree/main/pom.xml) as well. 

```yaml

  test:
    name: Build and Unit Test
    runs-on: ubuntu-latest
    
    steps:
      - name: Check out the code
        uses: actions/checkout@v1
        with:
          fetch-depth: 0
      - name: Set up JDK
        uses: actions/setup-java@v1
        with:
          java-version: 1.8
      - name: Cache Maven packages
        uses: actions/cache@v1
        with:
          path: ~/.m2
          key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
          restore-keys: ${{ runner.os }}-m2     
      - name: Build
        run: mvn -B clean package -DskipTests
      - name: Run UnitTest and Verify 
        run: mvn -B verify -DexcludedGroups="LamdaTest"
      - name: Generate JaCoCo Badge
        id: jacoco
        uses: cicirello/jacoco-badge-generator@v2
      - name: Log code coverage percentage
        run: |
          echo "coverage = ${{ steps.jacoco.outputs.coverage }}"
          echo "branch coverage = ${{ steps.jacoco.outputs.branches }}"
      - name: Upload code coverage report
        uses: actions/upload-artifact@v2
        with:
          name: jacoco-report
          path: target/site/jacoco/
      - name: Adding Junit Report
        uses: ashley-taylor/junit-report-annotations-action@master
        if: always()
        with:
          access-token: ${{ secrets.GITHUB_TOKEN }}          
      - name: Publish Unit Test Results
        uses: EnricoMi/publish-unit-test-result-action/composite@v1
        with:
           files: target/surefire-reports/*.xml

```

In [pom.xml](https://github.com/judebantony/continuoustesting-github-action-example/tree/main/pom.xml), we need to add the jacoco plugin.

GitHub Action:-
![githubaction](./doc/githubaction.png)

### 3) Functional Web UI Test - Using LamdaTest. ###

Run your [Selenium](https://www.selenium.dev) test automation scripts across online selenium grid of desktop, Android and iOS mobile browsers. Develop, test, and deliver faster every time with automated cross browser testing using LambdaTest online Automation Browser Testing Grid.

Execute the [Selenium](https://www.selenium.dev) [Cucumber](https://cucumber.io) based UI Test Cases using [LamdaTest](https://www.lambdatest.com/?fp_ref=aliakbar42) and capture the result. The feature files are present [here](https://github.com/judebantony/continuoustesting-github-action-example/tree/main/src/test/resources/com/jba/ci/ct/bdd/).

```yaml 

  lamdaTest:
    name: 'LamdaTest QA Test Validation'
    runs-on: ubuntu-latest 
    needs: [test]
    
    steps:
      - name: Start Tunnel
        id: tunnel
        uses: LambdaTest/LambdaTest-tunnel-action@v1
        with:
          user: ${{ secrets.LT_EMAIL }}
          accessKey: ${{ secrets.LT_ACCESS_KEY }}
          tunnelName: "testTunnel"
      - name: Check out the code
        uses: actions/checkout@v1
        with:
          fetch-depth: 0
      - name: Set up JDK
        uses: actions/setup-java@v1
        with:
          java-version: 1.8
      - name: Cache Maven packages
        uses: actions/cache@v1
        with:
          path: ~/.m2
          key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
          restore-keys: ${{ runner.os }}-m2     
      - name: Run LamdaTest Automation
        run: mvn -B verify -Dgroups="LamdaTest"
        env: 
          CUCUMBER_PUBLISH_TOKEN: ${{secrets.CUCUMBER_PUBLISH_TOKEN}}
          LT_EMAIL: ${{ secrets.LT_EMAIL }}
          LT_ACCESS_KEY: ${{ secrets.LT_ACCESS_KEY }}
      - name: Export Tunnel Logs for debugging
        uses: actions/upload-artifact@v2
        with:
           name: tunnel_logs
           path: ${{ steps.tunnel.outputs.logFileName }}          

```
LambdaTest dashboard:-
![lamdatest](./doc/lamdatest.png)

LambdaTest Test Result:-
![lamdatest1](./doc/lamdatest1.png)
