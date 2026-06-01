# CI Pipeline Learning Exercise

## Objective

Verify that the GitHub Actions CI pipeline is working correctly by intentionally breaking a test and observing the pipeline failure.

## Current Status

There is an **intentionally failing test** in `src/test/java/org/example/cicd2/service/ComponentServiceImplTest.java`:

```java
@Test
void intentionallyFailingTest_verifyPipelineDetectsFail() {
    assertEquals(1, 2, "This test intentionally fails to verify CI pipeline is working");
}
```

This test will fail because `1 ≠ 2`.

---

## Exercise Steps

### Step 1: Push the failing test

Commit and push the code with the failing test to your repository:

```bash
git add -A
git commit -m "Add intentional failing test to verify CI pipeline detection"
git push origin main
```

(Replace `main` with your branch name if needed.)

### Step 2: Monitor the pipeline

1. Go to your GitHub repository
2. Click on the **Actions** tab
3. Observe the workflow run with name `CI` (or `Publish Release` if pushing to main)
4. Watch it execute:
   - Checkout
   - Setup Java 17
   - Cache Maven
   - **Build (run tests + coverage)** ← This step will **FAIL**

### Step 3: Check the logs

1. Click on the failed workflow run
2. Expand **Build (run tests + coverage)**
3. Scroll to the test output section
4. Look for:
   ```
   FAILURE: intentionallyFailingTest_verifyPipelineDetectsFail
   AssertionError: This test intentionally fails to verify CI pipeline is working
   ```

---

## What this proves

✅ The GitHub Actions CI pipeline is **detecting test failures**  
✅ The pipeline is **reporting failures** clearly in the logs  
✅ The pipeline is **blocking** (not allowing) failed builds to proceed  

---

## Step 4: Fix and verify recovery

Once you've observed the failure, fix the test by deleting it or making it pass:

### Option A: Delete the test

Edit `src/test/java/org/example/cicd2/service/ComponentServiceImplTest.java` and remove the `intentionallyFailingTest_verifyPipelineDetectsFail()` method entirely.

### Option B: Fix the test

Change the assertion to pass:

```java
@Test
void intentionallyFailingTest_verifyPipelineDetectsFail() {
    assertEquals(1, 1, "Fixed test now passes");
}
```

### Step 5: Commit and push the fix

```bash
git add -A
git commit -m "Fix failing test - CI pipeline recovery exercise"
git push origin main
```

### Step 6: Verify the pipeline now succeeds

1. Return to the **Actions** tab
2. Watch the new workflow run
3. Confirm all steps pass, including:
   - Build (run tests + coverage) ✅
   - Coverage report generated ✅
   - Artifact uploaded ✅

---

## Key Takeaways

| Concept | What we learned |
|---------|-----------------|
| **Failure Detection** | CI catches test failures immediately |
| **Fast Feedback** | Logs show exactly which test failed and why |
| **Blocking** | Failed tests prevent artifact upload & deployment |
| **Recovery** | Fixing the test and re-pushing triggers a new, successful run |

---

## Next CI Learning Exercises

After this exercise, try:

1. **Break a validation rule** - Update a test to intentionally violate Spring Validation (e.g., send empty componentName) and observe the 400 Bad Request in controller tests.

2. **Introduce a logic bug** - Change a method in `ComponentServiceImpl` (e.g., return wrong ID) and watch tests catch it.

3. **Tweak coverage thresholds** - Lower the JaCoCo minimum coverage in `pom.xml` to observe coverage enforcement in the CI pipeline.

4. **Trigger on pull requests** - Create a pull request (instead of pushing to main) and observe the CI pipeline running on PR validation.

---

## Questions?

Check the `skills.md` file for a full breakdown of CI/CD concepts and skills covered in this project.

