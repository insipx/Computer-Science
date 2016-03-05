	.file	"FactorialRM.c"
	.text
	.globl	main
	.align	16, 0x90
	.type	main,@function
main:                                   # @main
# BB#0:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$40, %esp
	leal	.L.str, %eax
	movl	%eax, (%esp)
	calll	printf
	leal	.L.str1, %ecx
	leal	-4(%ebp), %edx
	movl	%ecx, (%esp)
	movl	%edx, 4(%esp)
	movl	%eax, -12(%ebp)         # 4-byte Spill
	calll	scanf
	movl	$64, %ecx
	movl	$64, (%esp)
	movl	%eax, -16(%ebp)         # 4-byte Spill
	movl	%ecx, -20(%ebp)         # 4-byte Spill
	calll	DumpS
	movl	-4(%ebp), %eax
	movl	%eax, (%esp)
	calll	factorial
	movl	$64, %ecx
	movl	%eax, -8(%ebp)
	movl	$64, (%esp)
	movl	%ecx, -24(%ebp)         # 4-byte Spill
	calll	DumpS
	leal	.L.str2, %eax
	movl	-4(%ebp), %ecx
	movl	-8(%ebp), %edx
	movl	%eax, (%esp)
	movl	%ecx, 4(%esp)
	movl	%edx, 8(%esp)
	calll	printf
	movl	$0, %ecx
	movl	%eax, -28(%ebp)         # 4-byte Spill
	movl	%ecx, %eax
	addl	$40, %esp
	popl	%ebp
	ret
.Ltmp0:
	.size	main, .Ltmp0-main

	.type	.L.str,@object          # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"Enter an integer:"
	.size	.L.str, 18

	.type	.L.str1,@object         # @.str1
.L.str1:
	.asciz	"%d"
	.size	.L.str1, 3

	.type	.L.str2,@object         # @.str2
.L.str2:
	.asciz	"%d! is %d\n"
	.size	.L.str2, 11


	.ident	"FreeBSD clang version 3.4.1 (tags/RELEASE_34/dot1-final 208032) 20140512"
	.section	".note.GNU-stack","",@progbits
