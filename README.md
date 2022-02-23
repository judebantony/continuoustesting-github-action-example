# Continuous Testing with Synthetic Data using GitHub Action #
[![Main Branch](https://github.com/judebantony/continuoustesting-github-action-example/actions/workflows/workflow.yml/badge.svg)](https://github.com/judebantony/continuoustesting-github-action-example/actions/workflows/workflow.yml)

## Continuous Testing in DevSecOps
Continuous testing in DevSecOps is a type of software testing that involves testing at every stage of the development life cycle. The goal of continuous testing is to evaluate the quality of the software as part of a continuous delivery process, by testing early and often. 

The old way of testing was hand off centric. The software was handed off from one team to another. A project would have definite Development and QA phases. QA teams always wanted more time to ensure quality. The goal was that the quality should prevail over project schedule.

Businesses of today, however, business wants faster delivery of software to the end user. The newer is the software, the better it can be marketed and increase revenue potential of the company. Hence, a new way of testing was evolved. Continuous Testing is a procedure of testing early, testing regular, test everywhere, and automate. Continuous means undisrupted testing done on a continuous basis. In a Continuous DevSecOps process, a software change continuously moves from Development to Testing to Deployment. The This process helps us to find the risk, address them and improve the quality of the product.

## Data Driven Testing 

Data-driven testing (DDT), also known as table-driven testing or parameterized testing, is a software testing methodology that is used in the testing of computer software to describe testing done using a table of conditions directly as test inputs and verifiable outputs as well as the process where test environment settings and control are not hard-coded. In the simplest form the tester supplies the inputs from a row in the table and expects the outputs which occur in the same row. The table typically contains values which correspond to boundary or partition input spaces. In the control methodology, test configuration is "read" from a database/csv/excel.

[Cucumber](https://cucumber.io) is an open-source testing framework that supports Behavior Driven Development for automation testing of web applications. The tests are first written in a simple scenario form that describes the expected behavior of the system from the user’s perspective. It explains the behavior of the application in a simple English text using [Gherkin](https://cucumber.io/docs/gherkin/) language.

[Selenium](https://www.selenium.dev) is an open-source umbrella project for a range of tools and libraries aimed at supporting web browser automation. Selenium provides a playback tool for authoring functional tests without the need to learn a test scripting language


## Synthetic Data for Privacy Preservation

Software engineers and data scientists often need access to large volumes of real data to develop, experiment, and innovate. Collecting such data unfortunately also introduces security liabilities and privacy concerns which affect individuals, organizations, and society at large. Data containing Personally Identifiable Information (PII) and Personal Health Information (PHI) are particularly vulnerable to disclosure, and need to be protected.

Regulations such as the General Data Protection Regulation (GDPR) serve to provide a level of legal protection for user data, but consequently introduce new technical challenges by restricting data usage, collection, and storage methods. In light of this, synthetic data could serve as a viable solution to protect user data privacy, stay compliant with regulations, and still maintain the pace and ability for development and innovation.

Synthetic data generation (SDG) is rapidly emerging as a practical privacy enhancing technology (PET) for sharing data for secondary purposes. It does so by generating non-identifiable datasets that can be used and disclosed without the legislative need for additional consent given that these datasets would not be considered personal information.

Privacy engineering expertise delivered to you as easy to use tools and APIs. Synthesize, Classify and Transform your data in minutes. Build trust, and innovate faster for your users. [Gretel](https://gretel.ai/) make it simple to create anonymized and synthetic datasets so you can work safely with data while preserving privacy.

## Integrating Continuous Testing tools using GitHub Action ##
Integration & Implementation of E2E CI/CD release workflow using [Github Action](https://github.com/features/actions), this has been achieved using different Cloud SaaS tools listed below.