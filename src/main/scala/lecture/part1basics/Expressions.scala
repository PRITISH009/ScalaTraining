package lecture.part1basics

object Expressions extends App {
   val x = 1 + 2  // Right hand side evaluates to 3 which is an expression
   println(x)
   
   println(1 + 2 * 4)
   // + - * / & | << >> >>> (Specific to scala with zero extension)

   println(1 == x)
   // == != > < >= <=

   var aVariable = 2
   aVariable += 3
   println(aVariable)
   // -= += *= /= These only work with variables (side effects)

   // Instructions vs Expressions

   // Instruction -> Something that you tell the computer to do
   // Expression -> Something that has a value or a type. An Expression gives back a value. In scala for example if is not
   // an instruction but an expression.
   
   val aCondition = true
   val aConditionedValue = if(aCondition) 5 else 7
   println(aConditionedValue)

   var i = 0

   while(i < 10) {
     println(i)
     i += 1
   }

   // NEVER WRITE THIS AGAIN.

   // Everything in Scala is an expression. Scala forces everything to evaluate to an expression.

   val aWeirdValue = (aVariable = 3) // Unit == void
   println(aWeirdValue)

   // Side effects are expressions in scala that returns units.
   // The While loop that we wrote sometime back is a side effect. Hence it returns a unit.

   // Examples of side effects are : println(), loops, reassigning

   // Code Blocks -> Special types of Expressions

   val aCodeBlock = {
     val y = 2
     val z = y + 1
     if(z > 2) "Hello" else "GoodBye"
   }

   println(aCodeBlock)
   // CodeBlock is also an expression. the value of the block is the value of its last expression.


}
