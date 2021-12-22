<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp"/>

<body>

	<section id="section" class="vh-100" style="background-color: #eee;">
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-sm-4 col-md-6 col-lg-8">

					<div class="card table-responsive">
						<div class="card-header p-3, form-inline justify-content-center">
							<h2 class="mb-0">Punchlist</h2>
						</div>
						<div class="card-body" data-mdb-perfect-scrollbar="true"
							style="position: relative; height: 400px">
						
						<c:if test="${requestScope.error != null}">
							<div id="alert" class="alert alert-danger" role="alert">
							<c:out value="${requestScope.error}"></c:out>
							</div>
						</c:if>

							<div class="row justify-content-center">
								<div class="col-auto">
									<table class="table mb-0 table-responsive">
										<form method="post" action="<%= request.getContextPath() %>/AccountController">
											<input type="hidden" name="cmd" value="attemptLogin">
											<h5 class="mb-0 text-center">Log In</h5>
											<tbody>
												<tr class="fw-normal">
													<td>Email</td>
												</tr>
												<tr class="fw-normal">
													<td><input type="text" name="email"></td>
												</tr>
												<tr class="fw-normal">
													<td>Password</td>
												</tr>
												<trclass="fw-normal">
												<td><input type="password" name="password"></td>
												</tr>
												<tr class="fw-normal">
													<td><button type=submit value="Login"
															class="btn btn-primary" styles="width: 30px;">Log
															In</button> <a href="<%= request.getContextPath() %>/createAccount.jsp">Create new
															account</a></td>
												</tr>
											</tbody>
										</form>
									</table>
								</div>
							</div>

						</div>
					</div>
				</div>
	</section>

</body>

<jsp:include page="footer.jsp"/>